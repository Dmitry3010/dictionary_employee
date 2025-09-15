package demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    //private static final String MESSAGE = "ID не нашелся в списке";

    public NotFoundException(String message) {
        super();
    }
}
