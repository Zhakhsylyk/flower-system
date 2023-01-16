package kz.aitu.sdp;

abstract class Component {
    private int component_id;
    private double component_price;
    private String component_color;
    private String component_name;

    public double getComponent_price() {
        return component_price;
    }

    public int getComponent_id() {
        return component_id;
    }

    public String getComponent_color() {
        return component_color;
    }

    public String getComponent_name() {
        return component_name;
    }

    public void setComponent_name(String component_name) {
        this.component_name = component_name;
    }

    public void setComponent_price(double component_price) {
        this.component_price = component_price;
    }

    public void setComponent_color(String component_color) {
        this.component_color = component_color;
    }

    public void setComponent_id(int component_id) {
        this.component_id = component_id;
    }

    public abstract String toString();
}
