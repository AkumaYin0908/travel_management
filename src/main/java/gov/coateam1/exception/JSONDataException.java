package gov.coateam1.exception;



public class JSONDataException extends RuntimeException{

    private String fileName;
    private String objectName;

    public JSONDataException(String message) {
        super(message);
    }

    public JSONDataException(String fileName, String objectName) {
        super(String.format("Failed to load %s data from %s ",objectName,fileName));
        this.fileName = fileName;
        this.objectName = objectName;
    }
}
