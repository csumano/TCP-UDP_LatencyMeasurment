package com.csc445.assignment1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class TCPClient {

    private String host;
    private int port;

    public TCPClient(String host, int port) throws IOException {

        this.host = host;
        this.port = port;
    }

    // send bytes to the server and get rtt
    public long sendMessage(int size) throws IOException{

        byte[] bytes = new byte[size];

        Socket socket = new Socket(host, port);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        long startTime = System.nanoTime();
        output.write(bytes);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        for (int i =0; i < size; i++) {
            bytes[i] = input.readByte();
        }

        long finalTime = System.nanoTime() - startTime;
        System.out.println("TCP RTT = " + finalTime);

        input.close();
        output.close();
        socket.close();

        return finalTime;

    }

    // send 1mb of data
    public long send1mb(int message, int size) throws IOException {
        Socket socket = new Socket(host, port);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        byte[] bytes = new byte[message];
        byte[] echo = new byte[size];
        Arrays.fill(bytes,(byte)1);

        long startTime = System.nanoTime();

        //go through each message size , write the byte then read it
        for (int i = 0; i < message; i++) {
            output.write(echo);
            bytes[i] = input.readByte();
        }

        long finalTime = System.nanoTime() - startTime;

        System.out.println("Time = " + finalTime);

        input.close();
        output.close();
        socket.close();


        return finalTime;

    }

}
