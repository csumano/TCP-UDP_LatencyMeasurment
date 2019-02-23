import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {

    String host = "localhost";
    int port = 2699;
    Socket socket;

    public TCPClient() throws IOException {
        socket = new Socket(host, port);

    }

    public void sendMessage(int size) throws IOException{

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());


        long startTime = System.nanoTime();

        output.write(new byte[size]);

        DataInputStream input = new DataInputStream(socket.getInputStream());

        long finalTime = System.nanoTime() - startTime;

        System.out.println("Total time = " + finalTime);


    }


}
