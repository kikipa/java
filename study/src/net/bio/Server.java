package net.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/27 12:57
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //1.create server socket
        //2.bind a port
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("tomcat server startup succeed!");
        //3.loop to listen client's request
        while(!serverSocket.isClosed()){
            //4.create a client request socket
            //attention: serverSocket.accept() blocks until a connection is made
            Socket  request = serverSocket.accept();
            System.out.println("received a new connection: "+request.toString());
            //5.handle a request in a thread
            try{
                //5.1 receive data: use java.io.InputStream
                InputStream inputStream = request.getInputStream();
                System.out.println("received request: ");
                //5.2 read data: use java.io.BufferReader
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String msg = null;
                //attention: reader will be blocked when there's no data
                while ((msg=reader.readLine())!=null){
                    //5.3 read msg line by line
                    if(msg.length()==0){
                        break;
                    }
                    System.out.println("received data: "+msg+" from: "+request.toString());
                }
                System.out.println("-----------------------------------end");
            }catch (IOException e){

            }finally {
                //
            }
        }
        //6. close the server socket
        //attention: any thread currently blocked in accept() will throw a SocketException
        serverSocket.close();

    }

}
