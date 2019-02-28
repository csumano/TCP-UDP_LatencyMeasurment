import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {

    Socket socket;
    String host;
    int port;

    public TCPClient(String host, int port) throws IOException {

        this.host = host;
        this.port = port;
    }

    public long sendMessage(int size) throws IOException{

        byte[] bytes = new byte[size];

        socket = new Socket(host, port);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        long startTime = System.nanoTime();
        output.write(bytes);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        for (int i =0; i< size; i++) {
            bytes[i] = input.readByte();
        }

        long finalTime = System.nanoTime() - startTime;
        System.out.println("TCP RTT = " + finalTime);

        input.close();
        output.close();
        socket.close();

        return finalTime;

    }

}
