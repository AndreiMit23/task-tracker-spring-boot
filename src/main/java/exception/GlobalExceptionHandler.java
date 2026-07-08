package exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFound(NoSuchElementException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalState(IllegalStateException exception){
        return exception.getMessage();
    }
}
