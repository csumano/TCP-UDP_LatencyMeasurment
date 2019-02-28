import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter 1 for server, enter 2 for client: ");
        int choice = s.nextInt();

        long test1;

        try {

            if(choice == 1){

                TCPserver tcpServer = new TCPserver(2699);

                tcpServer.start(1);
                tcpServer.start(64);
                tcpServer.start(1024);

                tcpServer.start(1024);

                UDPServer udpServer = new UDPServer(2699);

                udpServer.start(1);
                udpServer.start(64);
                udpServer.start(1024);



            } else if (choice == 2){
                TCPClient tcpClient = new TCPClient("localhost", 2699);

                tcpClient.sendMessage(1);
                tcpClient.sendMessage(64);
                tcpClient.sendMessage(1024);

                test1 = tcpClient.sendMessage(1024);

                throughputCalc(test1,1024);

                UDPClient udpClient = new UDPClient("localhost", 2699);

                udpClient.sendMessage(1);
                udpClient.sendMessage(64);
                udpClient.sendMessage(1024);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static double throughputCalc(long time, double size) {
        return size * 8 / (time * 1000000000);
    }


}
