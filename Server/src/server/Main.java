package server;

import controller.AttendAWaiter;
import controller.MenuController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
	    final int PORT = 35000;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            MenuController mc = new MenuController();
            while (true) {
                Socket socket = serverSocket.accept();
                new AttendAWaiter(socket, mc.getMenu()).start();
                System.out.println("Someone is attending a table");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
