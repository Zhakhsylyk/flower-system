package kz.aitu.sdp;

public class CustomerNotFound extends Throwable {
    public CustomerNotFound() {
        super("Client with that phone wasn't found!");
    }
}
