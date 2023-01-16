package kz.aitu.sdp;

public class Accessory extends Component {
    public Accessory() {
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
        return "Accessory {" +
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
