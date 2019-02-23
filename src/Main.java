import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        try {
            TCPserver server = new TCPserver();
            TCPClient client = new TCPClient();

            server.start(1);
            server.start(64);
            server.start(1024);



            client.sendMessage(1);
            client.sendMessage(64);
            client.sendMessage(1024);

        } catch (IOException e) {
            e.printStackTrace();
        }




//        try {
//            TCPserver server = new TCPserver();
//            TCPClient client = new TCPClient();
//            Thread thread1 =  new Thread(() -> {
//                try {
//                    server.start(3);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            thread1.start();
//            client.sendMessage(1);
//            client.sendMessage(64);
//            client.sendMessage(1024);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }







    }
}
