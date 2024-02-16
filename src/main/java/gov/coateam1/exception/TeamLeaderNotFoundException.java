package gov.coateam1.exception;

public class TeamLeaderNotFoundException extends RuntimeException{

    public TeamLeaderNotFoundException(String message) {
        super(message);
    }
}
