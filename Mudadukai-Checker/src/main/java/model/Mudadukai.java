package model;

import java.io.Serializable;


public class Mudadukai implements Serializable{
    private String name;
    private int price;

    public Mudadukai(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

