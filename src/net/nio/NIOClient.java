package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/1/14 10:09
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        //1. Client create a connection
        SocketChannel socketChannel = SocketChannel.open();
        //2. set non-blocking mode
        socketChannel.configureBlocking(false);
        //3. connect to server
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
        while (!socketChannel.finishConnect()){
            //wait until connected to server
            Thread.yield();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input: ");
        //4. send request data to server: write data to channel
        String msg = scanner.nextLine();
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        while (byteBuffer.hasRemaining()){
            socketChannel.write(byteBuffer);
        }
        //5. read response data from server: read data from buffer
        System.out.println("received response from server " + socketChannel.getRemoteAddress() + " : ");
        ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
        while(socketChannel.isOpen()&&socketChannel.read(responseBuffer)!=-1){
            //in long connection, we should judge when to break
            if(responseBuffer.position()>0){
                break;
            }
        }
        responseBuffer.flip();
        byte[] content = new byte[responseBuffer.limit()];
        responseBuffer.get(content);
        System.out.println(new String(content));
        scanner.close();
        //6. close the connection
        socketChannel.close();
    }
}
