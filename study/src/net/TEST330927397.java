package net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/23 22:01
 */
public class TEST330927397 {

    static byte[] request = null;
    static{
        StringBuffer temp = new StringBuffer();
        temp.append("GET http://baidu.com HTTP/1/1");
        request = temp.toString().getBytes();
    }
    
    //我的QQ号：12323，我的解析到百度服务器server类型是：Server: BWS/1.1
    public static void main(String[] args) throws IOException {
        try {
            final SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("baidu.com", 443));
            final Charset charset = Charset.forName("GBK");
            socketChannel.configureBlocking(false);

            while (!socketChannel.finishConnect()) {
                Thread.sleep(10);
            }
            socketChannel.write(ByteBuffer.wrap(request));

            int read = 0;
            boolean readed = false;
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while ((read = socketChannel.read(buffer)) != -1) {
                if (read == 0 && readed) {
                    break;
                } else if (read == 0) {
                    continue;
                }

                buffer.flip();
                String responseLine = charset.decode(buffer).toString();
                String[] lines = responseLine.split("\r\n");
                if(lines!=null&&lines.length>1){
                    System.out.println("我的QQ号：330927397，我的解析到百度服务器server类型是："+lines[1]);
                }
                buffer.clear();
                readed = true;
            }
            System.out.println("----------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
