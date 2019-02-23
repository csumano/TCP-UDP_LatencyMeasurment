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
        output.write(size);

        DataInputStream input = new DataInputStream(socket.getInputStream());

    }


}
