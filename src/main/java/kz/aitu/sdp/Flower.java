package kz.aitu.sdp;

public class Flower extends Component {
    public Flower() {
    }

    public Flower(int flower_id, String flower_name, String flower_color, double flower_price) {
        super.setComponent_id(flower_id);
        super.setComponent_name(flower_name);
        super.setComponent_color(flower_color);
        super.setComponent_price(flower_price);
    }

    @Override
    public void setComponent_color(String component_color) {
        super.setComponent_color(component_color);
    }

    @Override
    public void setComponent_id(int component_id) {
        super.setComponent_id(component_id);
    }

    @Override
    public String toString() {
        return "Flower {" +
                "ID:'" + getComponent_id() + '\'' +
                ", Name:'" + getComponent_name() + '\'' +
                ", Color:'" + getComponent_color() + '\'' +
                ", Price:" + getComponent_price() +
                '}';
    }

    @Override
    public void setComponent_name(String component_name) {
        super.setComponent_name(component_name);
    }

    @Override
    public void setComponent_price(double component_price) {
        super.setComponent_price(component_price);
    }
}
