package demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionDoubleEmployee extends RuntimeException{

    private static final String MESSAGE = "Такой кондидат уже существует";

    public NotFoundExceptionDoubleEmployee() {
        super(MESSAGE);
    }
}
