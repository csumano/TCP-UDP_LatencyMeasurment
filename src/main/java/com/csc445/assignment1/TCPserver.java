package com.csc445.assignment1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {

    private int port;
    private ServerSocket server;


    public TCPserver(int port) {

        this.port = port;

    }

    // start TCP server
    public void start(int size) {

        try {

            server = new ServerSocket(port);
            Socket socket = server.accept();

            System.out.println("TCP Server has started");

            byte[] bytes = new byte[size];

            DataInputStream input = new DataInputStream(socket.getInputStream());

            for (int i = 0; i < size; i++) {
                bytes[i] = input.readByte();
            }

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.write(bytes);

            input.close();
            output.close();
            socket.close();
            server.close();

            System.out.println("TCP Server has closed");


        } catch (IOException e){
            e.printStackTrace();
        }


    }

    // send 1mb of data
    public void server1mb(int message, int size) throws IOException {

        server = new ServerSocket(port);

        try{

            Socket socket = server.accept();

            System.out.println("TCP Server has started");

            byte[] bytes = new byte[size];
            byte echo = (byte) 1 ;

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            for (int i = 0; i < message; i++) {
                for (int j = 0; j < size; j++) {
                    bytes[j] = input.readByte();
                }
                output.write(echo);
            }

            server.close();
            input.close();
            output.close();
            socket.close();


            System.out.println("TCP Server has closed");

        } catch (IOException e){
            e.printStackTrace();

        }

    }

}
