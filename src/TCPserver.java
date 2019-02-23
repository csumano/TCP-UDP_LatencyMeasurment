import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPserver {

    ServerSocket server;
    int port = 2699;

    public TCPserver() throws IOException{

        server = new ServerSocket(port);

    }

    public void start(int size) throws IOException{
        // The client that connected to my server
        Socket socket = server.accept();
        System.out.println("Server has started");


        DataInputStream input = new DataInputStream(socket.getInputStream());

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        output.write(size);

        System.out.println("Size is:" + size);

//
//        // Scanner allows for getInputStream to be workable
//        Scanner s = new Scanner(clientSocket.getInputStream());
//
//
//        while (s.hasNext()){
//            System.out.print(s.next());
//        }
//
//        s.close();
    }




}
