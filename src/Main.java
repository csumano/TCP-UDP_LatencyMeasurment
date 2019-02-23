import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try {
            TCPserver server = new TCPserver();
            TCPClient client = new TCPClient();
            Thread thread1 =  new Thread(() -> {
                try {
                    server.start(3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread1.start();
            client.sendMessage(3);
        } catch (IOException e) {
            e.printStackTrace();
        }







    }
}
