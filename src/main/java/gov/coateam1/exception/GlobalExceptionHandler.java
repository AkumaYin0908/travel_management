package gov.coateam1.exception;

import gov.coateam1.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resoureNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();

        APIResponse apiResponse = new APIResponse(message,false, HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> exceptionHandler(Exception ex){
        String message = ex.getMessage();
        APIResponse apiResponse = new APIResponse(message,false,HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JSONDataException.class)
    public ResponseEntity<APIResponse> exceptionHandler(JSONDataException ex){
        String message = ex.getMessage();

        APIResponse apiResponse = new APIResponse(message,false,HttpStatus.INTERNAL_SERVER_ERROR.value());

        return  new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
