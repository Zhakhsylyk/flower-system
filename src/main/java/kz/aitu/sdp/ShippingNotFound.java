package kz.aitu.sdp;

public class ShippingNotFound extends Throwable {
    public ShippingNotFound() {
        super("Shipping with that id wasn't found!");
    }
}
