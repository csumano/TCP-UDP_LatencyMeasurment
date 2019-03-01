import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        // check user input for server or client
        Scanner s = new Scanner(System.in);
        System.out.println("Enter 1 for server, enter 2 for client: ");
        int choice = s.nextInt();

        long tcp1k, tcp16k, tcp64k, tcp256k, tcp1m;

        try {

            if(choice == 1){

                TCPserver tcpServer = new TCPserver(2699);

                //echo tcp responses
                tcpServer.start(1);
                tcpServer.start(64);
                tcpServer.start(1024);

                tcpServer.start(1024);
                tcpServer.start(16384);
                tcpServer.start(65536);
                tcpServer.start(262144);
                tcpServer.start(1048576);

                UDPServer udpServer = new UDPServer(2699);

                //echo udp responses
                udpServer.start(1);
                udpServer.start(64);
                udpServer.start(1024);



            } else if (choice == 2){
                TCPClient tcpClient = new TCPClient("localhost", 2699);

                tcpClient.sendMessage(1);
                tcpClient.sendMessage(64);
                tcpClient.sendMessage(1024);

                tcp1k = tcpClient.sendMessage(1024);
                tcp16k = tcpClient.sendMessage(16384);
                tcp64k = tcpClient.sendMessage(65536);
                tcp256k = tcpClient.sendMessage(262144);
                tcp1m = tcpClient.sendMessage(1048576);

                System.out.println(throughputCalc(tcp1k,1024));
                System.out.println(throughputCalc(tcp16k,16384));
                System.out.println(throughputCalc(tcp64k,65536));
                System.out.println(throughputCalc(tcp256k,262144));
                System.out.println(throughputCalc(tcp1m,1048576));


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
        return size * 8 / nanoToMicro(time);
    }

    private static double nanoToMicro(long time){
        return (time/1000);
    }




}
