package gov.coateam1.exception;

public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException(String message) {
        super(message);
    }
}
