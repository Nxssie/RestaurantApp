package controller;

import model.Food;
import model.Menu;

public class MenuController {

    private static Menu menu = new Menu();

    public MenuController() {
        loadMenu();
    }

    private static void loadMenu() {

        Food[] dishes = new Food[3];

        dishes[0] = new Food("Second", "Spaghetti Bolognese", 3);
        dishes[1] = new Food("First", "Soup", 1);
        dishes[2] = new Food("Dessert", "Vanilla Ice Cream", 2);

        System.out.println("Loading menu...");

        for (int i = 0; i < dishes.length; i++) {
            menu.getMenu().put(i,dishes[i]);
        }

        System.out.println("Menu loaded.");
    }

    public Menu getMenu() {
        return menu;
    }
}
