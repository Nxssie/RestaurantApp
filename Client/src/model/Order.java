package model;

import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable {

    HashMap<Integer, Food> order = new HashMap<>();

    public HashMap<Integer, Food> getOrder() {
        return order;
    }

    public void setOrder(HashMap<Integer, Food> order) {
        this.order = order;
    }
}
