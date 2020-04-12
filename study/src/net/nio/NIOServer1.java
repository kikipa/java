package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @description: roll polling multiple requests in one thread
 * @author: za-hejin
 * @time: 2020/1/14 10:38
 */
public class NIOServer1 {

    /**
     * list of connected connections
     * */
    private static ArrayList<SocketChannel> channels = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //1. Server create a channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2. set non-blocking mode
        serverSocketChannel.configureBlocking(false);
        //3. bind a port: 8080
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("NIO Server started!");
        //4. loop to listen TCP request on the port
        while(true){
            //5. get a new TCP connect channel (ServerSocketChannel is set as non-blocking before)
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel!=null){
                //6. received a new connection, handle a TCP request read/response
                System.out.println("received a new connection: " + socketChannel.getRemoteAddress());
                //set SocketChannel as non-blocking
                socketChannel.configureBlocking(false);
                //just add connection to list when a new connection setup
                channels.add(socketChannel);
            }else {
                //when there is no connection, loop the connection list to handle request data and response data
                Iterator<SocketChannel> iterator = channels.iterator();
                while (iterator.hasNext()){
                    SocketChannel channel = iterator.next();
                    try{
                        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);

                        //if there is no data in this channel, continue loop, handle other channel
                        if(channel.read(requestBuffer)==0){
                            continue;
                        }

                        while(channel.isOpen()&&channel.read(requestBuffer)!=-1){
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
                        System.out.println("received data from: " + channel.getRemoteAddress());

                        //response 200
                        String response = "HTTP/1.1 200 OK\r\n" +
                                "Content-Length: 11\r\n" +
                                "Hello World";
                        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                        while(buffer.hasRemaining()) {
                            channel.write(buffer);
                        }
                        //after finished handle this channel, remove it from list
                        iterator.remove();
                    }catch (IOException ex){
                        ex.printStackTrace();
                        iterator.remove();
                    }
                }
            }
        }
    }
}
