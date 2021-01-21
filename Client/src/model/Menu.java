package model;

import java.io.Serializable;
import java.util.HashMap;

public class Menu implements Serializable {

    private HashMap<Integer, Food> menu = new HashMap<>();

    public HashMap<Integer, Food> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<Integer, Food> menu) {
        this.menu = menu;
    }
}
