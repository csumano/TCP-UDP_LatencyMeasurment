import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPServer {

    int port;
    DatagramSocket datagramSocket;
    ServerSocket serverSocket;

    public UDPServer(int port){
        this.port = port;
    }

    public void start(int size){

        try {

            datagramSocket = new DatagramSocket(port);
            byte[] bytes = new byte[size];

            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

            System.out.println("UDP Server has started");
            serverSocket = new ServerSocket(port);

            for (int i = 0; i < size; i++) {
                datagramSocket.receive(packet);
                packet = new DatagramPacket(bytes, bytes.length, packet.getAddress(), packet.getPort());
                datagramSocket.send(packet);

            }

            serverSocket.close();
            datagramSocket.close();

            System.out.println("UDP Server has closed");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
