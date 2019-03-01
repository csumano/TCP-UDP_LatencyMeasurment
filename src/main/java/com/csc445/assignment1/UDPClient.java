package com.csc445.assignment1;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;


public class UDPClient {

    private int port;
    private String host;


    public UDPClient(String host, int port) {

        this.host = host;
        this.port =  port;

    }

    // send bytes to the UDP server and get rtt
    public long sendMessage(int size) throws IOException{

        DatagramSocket clientSocket = new DatagramSocket();
        byte[] bytes = new byte[size];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), port);

        long startTime = System.nanoTime();

        for (int i = 0; i < size; i++) {
            clientSocket.send(packet);
            packet = new DatagramPacket(bytes, size);
            clientSocket.receive(packet);

        }

        long finalTime = System.nanoTime() - startTime;
        System.out.println("UDP RTT = " + finalTime);

        clientSocket.close();

        return finalTime;

    }

    // send 1mb of data
    public long send1MB(int message, int size) throws IOException{

        byte [] bytes = new byte[size];
        Arrays.fill(bytes, (byte)1);
        byte [] response = new byte [1];

        DatagramSocket clientSocket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), port);



        long start = System.nanoTime();

        for (int i = 0; i< message; i++) {
            clientSocket.send(packet);
            DatagramPacket resp = new DatagramPacket(response, response.length);
            clientSocket.receive(resp);
        }

        long totalTime = System.nanoTime() - start;

        System.out.println("Time = " + totalTime);

        clientSocket.close();

        return totalTime;
    }


}
