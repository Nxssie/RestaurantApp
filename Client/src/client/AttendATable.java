package client;

import model.Menu;
import model.Order;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class AttendATable {

    private Menu menu = new Menu();

    public AttendATable(Socket socket) {
        System.out.println("Attending a table");
        loadMenu(socket);
        SendInfoToServer(socket);
    }

    private void SendInfoToServer(Socket socket) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Send some orders to server");
            Scanner sc = new Scanner(System.in);
            Order orderToSend = new Order();

            System.out.println("How many dishes has the order?");
            int orderSize = sc.nextInt();

            for (int i = 0; i < orderSize; i++) {
                System.out.println(i);
                System.out.println("Introduce dish number.");
                System.out.println("For example: 0. Spaghetti Bolognese");
                int dishNumber = sc.nextInt();

                System.out.println("Do you want to add " + menu.getMenu().get(dishNumber).getName() + " to order?");
                System.out.println("Answer with y or n.");

                Scanner confirmScanner = new Scanner(System.in);
                String confirmation = confirmScanner.nextLine();

                if (confirmation.equals("y")) {
                    orderToSend.getOrder().put(i, menu.getMenu().get(dishNumber));
                    System.out.println(menu.getMenu().get(dishNumber).getName() + " added to order.");
                } else if (confirmation.equals("n")) {
                    System.out.println("Dish not added.");
                } else {
                    System.out.println("That's not an y or n.");
                }
            }

            oos.writeObject(orderToSend);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMenu(Socket socket) {
        System.out.println("Receiving menu...");

        InputStream is;

        try {
            is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            menu = (Menu) ois.readObject();

            System.out.println("Available menu");
            for (int i = 0; i < menu.getMenu().size(); i++) {
                System.out.println(i + ". " + menu.getMenu().get(i).getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
