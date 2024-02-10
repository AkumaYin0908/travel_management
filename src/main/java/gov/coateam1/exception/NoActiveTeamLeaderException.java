package gov.coateam1.exception;

public class NoActiveTeamLeaderException extends RuntimeException{

    public NoActiveTeamLeaderException(String message) {
        super(message);
    }
}
