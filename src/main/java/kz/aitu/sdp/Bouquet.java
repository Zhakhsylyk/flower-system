package kz.aitu.sdp;

import java.util.ArrayList;
import java.util.List;

public class Bouquet {
    private int bouquet_id;
    private String bouquet_fillings_string;
    private List<Component> bouquet_fillings = new ArrayList<Component>();
    private double bouquet_price;

    public Bouquet() {
        double total_price = 0;
        for (Component component : bouquet_fillings) {
            total_price += component.getComponent_price();
        }
    }

    public String toStringDBE() {
        return bouquet_fillings_string;
    }

    public String toString() {
        return bouquet_fillingsToString();
    }

    public void addComponent(Component component) {
        bouquet_fillings.add(component);
    }

    public int getBouquet_id() {
        return bouquet_id;
    }

    public void setBouquet_id(int bouquet_id) {
        this.bouquet_id = bouquet_id;
    }

    public List<Component> getBouquet_fillings() {
        return bouquet_fillings;
    }

    public void setBouquet_fillings(List<Component> bouquet_fillings) {
        this.bouquet_fillings = bouquet_fillings;
    }

    public double getBouquet_price() {
        double total_price = 0;
        for (Component component : bouquet_fillings) {
            total_price += component.getComponent_price();
        }
        bouquet_price = total_price;
        return bouquet_price;
    }

    public void setBouquet_price(double bouquet_price) {
        this.bouquet_price = bouquet_price;
    }

    public String getBouquet_fillings_string() {
        return bouquet_fillings_string;
    }

    public void setBouquet_fillings_string(String bouquet_fillings_string) {
        this.bouquet_fillings_string = bouquet_fillings_string;
    }

    public String bouquet_fillingsToString() {
        String list = "";
        for (Component component : bouquet_fillings) {
            list += component.getComponent_name() + ", ";
        }
        list = list.substring(0, list.length() - 2);
        return list;
    }
}
