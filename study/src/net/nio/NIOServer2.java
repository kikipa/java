package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/1/14 14:15
 */
public class NIOServer2 {

    public static void main(String[] args) throws IOException {
        //1. Server create a channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //set non-blocking mode
        serverSocketChannel.configureBlocking(false);

        //2. create a selector, and register channels in the selector
        Selector selector = Selector.open();
        //register ServerSocketChannel in selector
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, serverSocketChannel);
        //set interest event: OP_ACCEPT
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        //3. bind a port: 8080
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("NIO Server started!");
        //4. loop to listen TCP request on the port
        //No longer to roll polling channels, but event instead
        while(true){
            //select method is blocking when there is no event!
            selector.select();

            //get events
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //loop events
            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while(iter.hasNext()){
                //encapsulated select result
                SelectionKey key = iter.next();
                iter.remove();

                //concern Read and Accept events
                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel)key.attachment();
                    //get client SocketChannel and register in the selector
                    SocketChannel clientSocketChannel = server.accept();
                    clientSocketChannel.configureBlocking(false);
                    //register the connection to the selector and listen the Read event! (Here we do not register Read event in a new selector)
                    clientSocketChannel.register(selector,SelectionKey.OP_READ, clientSocketChannel);
                    System.out.println("received a new connection: " + clientSocketChannel.getRemoteAddress());
                }
                if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)key.attachment();
                    try{
                        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                        //if there is no data in this channel, continue loop, handle other channel
                        if(socketChannel.read(requestBuffer)==0){
                            continue;
                        }
                        while(socketChannel.isOpen()&&socketChannel.read(requestBuffer)!=-1){
                            //in long connection, we should judge when to break;
                            if(requestBuffer.position()>0){
                                break;
                            }
                        }
                        if(requestBuffer.position()==0){
                            //if there is no data, continue to loop
                            continue;
                        }
                        requestBuffer.flip();
                        byte[] content = new byte[requestBuffer.limit()];
                        requestBuffer.get(content);
                        System.out.println(new String(content));
                        System.out.println("received data from: " + socketChannel.getRemoteAddress());
                        //response 200
                        String response = "HTTP/1.1 200 OK\r\n" +
                                "Content-Length: 11\r\n" +
                                "Hello World";
                        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                        while(buffer.hasRemaining()) {
                            socketChannel.write(buffer);
                        }
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
