package gov.coateam1.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long longValue;
    private String strValue;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, long longValue) {
        super(String.format("%s not found with this %s : %d!",resourceName,fieldName,longValue));
        this.resourceName = resourceName;
        this.fieldName=fieldName;
        this.longValue=longValue;
    }

    public ResourceNotFoundException(String resourceName,String fieldName,String strValue) {
        super(String.format("%s not found with this %s : %s!",resourceName,fieldName,strValue));
        this.resourceName = resourceName;
        this.fieldName=fieldName;
        this.strValue=strValue;
    }
}
