package kz.aitu.sdp;

public class Order {
    private int order_id;
    private Customer order_customer;
    private Bouquet order_bouquet;
    private Payment order_payment;
    private Employee order_employee;
    private String order_date;
    private Shipping order_shipping;

    public Shipping getOrder_shipping() {
        return order_shipping;
    }

    public void setOrder_shipping(Shipping order_shipping) {
        this.order_shipping = order_shipping;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Customer getOrder_customer() {
        return order_customer;
    }

    public void setOrder_customer(Customer order_customer) {
        this.order_customer = order_customer;
    }

    public Bouquet getOrder_bouquet() {
        return order_bouquet;
    }

    public void setOrder_bouquet(Bouquet order_bouquet) {
        this.order_bouquet = order_bouquet;
    }

    public Payment getOrder_payment() {
        return order_payment;
    }

    public void setOrder_payment(Payment order_payment) {
        this.order_payment = order_payment;
    }

    public Employee getOrder_employee() {
        return order_employee;
    }

    public void setOrder_employee(Employee order_employee) {
        this.order_employee = order_employee;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", order_customer=" + order_customer +
                ", order_bouquet=" + order_bouquet.toStringDBE() +
                ", order_payment=" + order_payment +
                ", order_employee=" + order_employee +
                ", order_date='" + order_date + '\'' +
                ", order_shipping=" + order_shipping +
                '}';
    }
}
