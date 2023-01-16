package kz.aitu.sdp;

public class PaymentNotFound extends Throwable {
    public PaymentNotFound() {
        super("Payment with that id wasn't found!");
    }
}
