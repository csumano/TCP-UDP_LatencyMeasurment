import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPserver {

    ServerSocket server;
    int port;

    public TCPserver(int port) {

        this.port = port;

    }

    public void start(int size) {


        try {

            server = new ServerSocket(port);

            Socket socket = server.accept();
            System.out.println("TCP Server has started");

            byte[] bytes = new byte[size];

            DataInputStream input = new DataInputStream(socket.getInputStream());

            for (int i = 0; i < size; i++) {
                bytes[i] = input.readByte();
            }

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.write(bytes);

            input.close();
            output.close();
            socket.close();
            server.close();

            System.out.println("TCP Server has closed");


        } catch (IOException e){
            e.printStackTrace();
        }


    }

}
