import java.io.IOException;
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

            System.out.println("UDP Server has started");

            byte[] bytes = new byte[size];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            serverSocket = new ServerSocket(port);
            datagramSocket.receive(packet);
            packet = new DatagramPacket(bytes, bytes.length, packet.getAddress(), packet.getPort());
            datagramSocket.send(packet);

            serverSocket.close();
            datagramSocket.close();

            System.out.println("UDP Server has stopped");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
