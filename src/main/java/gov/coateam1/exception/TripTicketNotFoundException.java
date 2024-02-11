package gov.coateam1.exception;

public class TripTicketNotFoundException extends RuntimeException{

    public TripTicketNotFoundException(String message) {
        super(message);
    }
}
