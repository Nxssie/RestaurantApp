package controller;

import model.Menu;
import model.Order;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

/**
 * @author Nxssie
 */

public class AttendAWaiter extends Thread {

    private Socket socket;
    private Menu menu;

    public AttendAWaiter(Socket socket, Menu menu) {
        this.socket = socket;
        this.menu = menu;
    }

    @Override
    public void run() {
        Attend();
    }

    private void Attend() {
        ObjectInputStream ois;
        ObjectOutputStream oos;

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending menu to client...");
            oos.writeObject(menu);
            oos.flush();

            Inet4Address ipAddress = (Inet4Address) socket.getInetAddress();
            String ip = ipAddress.getHostAddress();

            ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                System.out.println("Order received from " + ip + ": ");
                try {
                    Order orderReceived = (Order) ois.readObject();

                    for (int i = 0; i < orderReceived.getOrder().size(); i++) {
                        System.out.println(orderReceived.getOrder().get(i).getName());
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("Send it to kitchen");
                ois.close();
                break;
            }

        } catch (SocketException se) {
            System.out.println("The waiter has finished his order");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
