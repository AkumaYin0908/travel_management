package gov.coateam1.exception;

public class TravelOrderNotFoundException extends RuntimeException{

    public TravelOrderNotFoundException(String message) {
        super(message);
    }
}
