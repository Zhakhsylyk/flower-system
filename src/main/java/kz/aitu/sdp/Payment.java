package kz.aitu.sdp;

public class Payment {
    private int payment_id;
    private String payment_status;
    private double payment_sum;

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public double getPayment_sum() {
        return payment_sum;
    }

    public void setPayment_sum(double payment_sum) {
        this.payment_sum = payment_sum;
    }

    @Override
    public String toString() {
        return "Payment{" + "payment_id=" + payment_id + ", payment_status='" + payment_status + '\'' + ", payment_sum=" + payment_sum + '}';
    }
}
