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

        socket = new Socket(host, port);
        long finalTime = 0;

        try {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            byte[] bytes = new byte[size];
            long startTime = System.nanoTime();
            output.write(bytes);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            finalTime = System.nanoTime() - startTime;
            System.out.println("TCP RTT = " + finalTime);

            input.close();
            output.close();
            socket.close();


        } catch (IOException e){
            e.printStackTrace();
        }

        return finalTime;

    }

}
