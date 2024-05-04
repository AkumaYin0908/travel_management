package gov.coateam1.exception;

import ch.qos.logback.core.model.processor.ModelHandlerException;
import gov.coateam1.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String, String> response = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(
                (objectError -> {
                    String fieldName = ((FieldError)objectError).getField();
                    String message = objectError.getDefaultMessage();
                    response.put(fieldName, message);
                })
        );

        return  new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }


}
