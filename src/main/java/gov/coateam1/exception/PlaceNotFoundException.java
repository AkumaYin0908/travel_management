package gov.coateam1.exception;

public class PlaceNotFoundException extends RuntimeException{

    public PlaceNotFoundException(String message) {
        super(message);
    }
}
