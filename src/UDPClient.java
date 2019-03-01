import java.io.IOException;
import java.net.*;


public class UDPClient {

    private DatagramSocket clientSocket;
    DatagramPacket packet;
    int port;
    String host;


    public UDPClient(String host, int port) {

        this.host = host;
        this.port =  port;

    }

    public long sendMessage(int size){

        long finalTime = 0;

        try{

            clientSocket = new DatagramSocket();
            byte[] bytes = new byte[size];
            packet = new DatagramPacket(bytes, size, InetAddress.getByName(host), port);

            long startTime = System.nanoTime();

            for (int i = 0; i < size; i++) {
                clientSocket.send(packet);
                packet = new DatagramPacket(bytes, size);
                clientSocket.receive(packet);

            }

            finalTime = System.nanoTime() - startTime;
            System.out.println("UDP RTT = " + finalTime);

            clientSocket.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        return finalTime;

    }
}
