package client;

import java.io.IOException;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        final String HOST = "192.168.103.33";
        final int PORT = 35000;

        try {
            System.out.println("hello, from client");
            Socket socket = new Socket(HOST, PORT);
            AttendATable attendATable = new AttendATable(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
