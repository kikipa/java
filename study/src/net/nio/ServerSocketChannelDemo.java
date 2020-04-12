package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/31 12:36
 */
public class ServerSocketChannelDemo {
    public static void main(String[] args) throws IOException {
        //1. Server create a channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2. set non-blocking mode
        serverSocketChannel.configureBlocking(false);
        //3. bind a port: 8080
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        //4. loop to listen TCP request on the port
        while(true){
            //5. get a new TCP connect channel
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel!=null){
                //6. handle a TCP request read/response
            }
        }
    }
}
