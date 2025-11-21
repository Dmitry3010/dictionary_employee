package demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class DuplicateException extends RuntimeException{

    public DuplicateException(String message) {
        super(message);
    }
}
