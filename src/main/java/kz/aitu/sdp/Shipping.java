package kz.aitu.sdp;

public class Shipping {
    private int shipping_id;
    private Employee shipping_employee;
    private String shipping_date;
    private String shipping_status;

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shopping_id) {
        this.shipping_id = shopping_id;
    }

    public Employee getShipping_employee() {
        return shipping_employee;
    }

    public void setShipping_employee(Employee shipping_employee) {
        this.shipping_employee = shipping_employee;
    }

    public String getShipping_date() {
        return shipping_date;
    }

    public void setShipping_date(String shipping_date) {
        this.shipping_date = shipping_date;
    }

    public String getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(String shipping_status) {
        this.shipping_status = shipping_status;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "shipping_id=" + shipping_id +
                ", shipping_employee=" + shipping_employee +
                ", shipping_date='" + shipping_date + '\'' +
                ", shipping_status='" + shipping_status + '\'' +
                '}';
    }
}
