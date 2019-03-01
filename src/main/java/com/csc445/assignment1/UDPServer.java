package com.csc445.assignment1;

import java.io.IOException;
import java.net.*;

public class UDPServer {

    private int port;

    public UDPServer(int port){
        this.port = port;
    }

    // start UDP server
    public void start(int size) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(port);

        byte[] bytes = new byte[size];

        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

        System.out.println("UDP Server has started");

        for (int i = 0; i < size; i++) {
            datagramSocket.receive(packet);
            packet = new DatagramPacket(bytes, bytes.length, packet.getAddress(), packet.getPort());
            datagramSocket.send(packet);

        }

        datagramSocket.close();

        System.out.println("UDP Server has closed");

    }
    // send 1mb of data
    public void server1MB(int message, int size) throws IOException{

        byte[] bytes = new byte[size];
        byte[] echo = {(byte) 1};

        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        DatagramSocket datagramSocket = new DatagramSocket(port);


        for (int i = 0; i < message ; i++) {
            datagramSocket.receive(packet);
            DatagramPacket response = new DatagramPacket(echo, echo.length, packet.getAddress(), packet.getPort());
            datagramSocket.send(response);

        }
        datagramSocket.close();
    }
}
