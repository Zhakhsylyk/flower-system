package kz.aitu.sdp;

public class Customer {
    private int customer_id;
    private String customer_fname;
    private String customer_lname;
    private String customer_address;
    private String customer_phone;
    private String customer_email;

    public Customer() {
    }

    public Customer(String customer_fname, String customer_lname, String customer_address, String customer_phone, String customer_email) {
        this.customer_fname = customer_fname;
        this.customer_lname = customer_lname;
        this.customer_address = customer_address;
        this.customer_phone = customer_phone;
        this.customer_email = customer_email;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_fname() {
        return customer_fname;
    }

    public void setCustomer_fname(String customer_fname) {
        this.customer_fname = customer_fname;
    }

    public String getCustomer_lname() {
        return customer_lname;
    }

    public void setCustomer_lname(String customer_lname) {
        this.customer_lname = customer_lname;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_fname='" + customer_fname + '\'' +
                ", customer_lname='" + customer_lname + '\'' +
                ", customer_address='" + customer_address + '\'' +
                ", customer_phone=" + customer_phone +
                ", customer_email='" + customer_email + '\'' +
                '}';
    }
}
