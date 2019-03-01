package com.csc445.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) {

        getIP();
        //get user input for server or client
        Scanner s = new Scanner(System.in);

        System.out.println("Enter 1 for server, enter 2 for client: ");
        int choice = Integer.parseInt(s.nextLine());

        long tcpRTT1B, tcpRTT64B, tcpRTT1024B, udpRTT1B, udpRTT64B, udpRTT1024B;

        long tcpTP1KB, tcpTP16KB, tcpTP64KB, tcpTP256KB, tcpTP1MB;

        long tcpI1024, tcpI2048, tcpI4096, udpI1024, udpI2048, udpI4096;

        try {

            if(choice == 1){

                //servers
                TCPserver tcpServer = new TCPserver(2699);
                UDPServer udpServer = new UDPServer(2699);

                //echo tcp responses for rtt
                tcpServer.start(1);
                tcpServer.start(64);
                tcpServer.start(1024);

                //echo tcp responses for throughput
                tcpServer.start(1024);
                tcpServer.start(16384);
                tcpServer.start(65536);
                tcpServer.start(262144);
                tcpServer.start(1048576);

                //echo tcp for 1mb
                tcpServer.server1mb(1024,1024);
                tcpServer.server1mb(2048,512);
                tcpServer.server1mb(4096,256);

                //echo udp responses
                udpServer.start(1);
                udpServer.start(64);
                udpServer.start(1024);

                //echo udp for 1mb
                udpServer.server1MB(1024,1024);
                udpServer.server1MB(2048,512);
                udpServer.server1MB(4096,256);



            } else if (choice == 2){

                //get user input for ip
                System.out.print("Server ip address: ");
                String ip = s.nextLine();
                System.out.println("");

                TCPClient tcpClient = new TCPClient(ip, 2699);
                UDPClient udpClient = new UDPClient(ip, 2699);

                System.out.println("RTT calculations for TCP in microseconds----------------");

                tcpRTT1B = tcpClient.sendMessage(1);
                tcpRTT64B = tcpClient.sendMessage(64);
                tcpRTT1024B = tcpClient.sendMessage(1024);

                System.out.println(nanoToMicro(tcpRTT1B));
                System.out.println(nanoToMicro(tcpRTT64B));
                System.out.println(nanoToMicro(tcpRTT1024B));

                System.out.println("Throughput calculations for TCP in Mpbs ----------------------");

                tcpTP1KB = tcpClient.sendMessage(1024);
                tcpTP16KB = tcpClient.sendMessage(16384);
                tcpTP64KB = tcpClient.sendMessage(65536);
                tcpTP256KB = tcpClient.sendMessage(262144);
                tcpTP1MB = tcpClient.sendMessage(1048576);


                System.out.println(throughputCalc(tcpTP1KB,1024));
                System.out.println(throughputCalc(tcpTP16KB,16384));
                System.out.println(throughputCalc(tcpTP64KB,65536));
                System.out.println(throughputCalc(tcpTP256KB,262144));
                System.out.println(throughputCalc(tcpTP1MB,1048576));

                System.out.println("1mb calucations for TCP in seconds----------------");

                tcpI1024 = tcpClient.send1mb(1024,1024);
                tcpI2048 = tcpClient.send1mb(2048,512);
                tcpI4096 =  tcpClient.send1mb(4096,256);


                System.out.println(nanoToSeconds(tcpI1024));
                System.out.println(nanoToSeconds(tcpI2048));
                System.out.println(nanoToSeconds(tcpI4096));

                System.out.println("RTT calculations for UDP in microseconds---------------");

                udpRTT1B = udpClient.sendMessage(1);
                udpRTT64B = udpClient.sendMessage(64);
                udpRTT1024B = udpClient.sendMessage(1024);

                System.out.println(nanoToMicro(udpRTT1B));
                System.out.println(nanoToMicro(udpRTT64B));
                System.out.println(nanoToMicro(udpRTT1024B));

                System.out.println("1mb calculations for UDP in seconds------------------");

                udpI1024 =  udpClient.send1MB(1024,1024);
                udpI2048 = udpClient.send1MB(2048,512);
                udpI4096 = udpClient.send1MB(4096,256);

                System.out.println(nanoToSeconds(udpI1024));
                System.out.println(nanoToSeconds(udpI2048));
                System.out.println(nanoToSeconds(udpI4096));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //get public ip
    private static void getIP() {
        String systemipaddress = "";
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            systemipaddress = sc.readLine().trim();
        }
        catch (Exception e) {
            systemipaddress = "Cannot Execute Properly";
        }
        System.out.println("Public IP Address: " + systemipaddress);

    }

    //calculate throughput
    private static double throughputCalc(long time, double size) {
        return size * 8 / nanoToMicro(time);
    }

    //nano to micro
    private static double nanoToMicro(long time){
        return (time/1000);
    }

    //nano to seconds
    private static double nanoToSeconds(long time){
        long millis = TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS);
        return (double) millis / 1000.0;
    }




}
