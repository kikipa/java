package net.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/27 20:24
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //1. create a socket connection to server: host:localhost,port:8080
        Socket socket = new Socket("localhost",8080);
        //2.1 write data: use OutputStream
        OutputStream outputStream = socket.getOutputStream();

        //2.2 data content: from keyboard input
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input:");
        String msg = scanner.nextLine();
        //2.3 write data to OutputStream
        //writer will be blocked when there's no data
        outputStream.write(msg.getBytes("UTF-8"));
        //3. close input
        scanner.close();
        //4. close current socket connection
        socket.close();
    }
}
