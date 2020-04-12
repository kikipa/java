package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/31 12:25
 */
public class SocketChannelDemo {
    public static void main(String[] args) throws IOException {
        //1. Client create a connection
        SocketChannel socketChannel = SocketChannel.open();
        //2. set non-blocking mode
        socketChannel.configureBlocking(false);
        //3. connect to server
        socketChannel.connect(new InetSocketAddress("http://163.com",80));
        //4. send request data to server: write data to channel
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.write(byteBuffer);
        //5. read response data from server: read data from buffer
        int bytesRead = socketChannel.read(byteBuffer);
        //6. close the connection
        socketChannel.close();
    }
}
