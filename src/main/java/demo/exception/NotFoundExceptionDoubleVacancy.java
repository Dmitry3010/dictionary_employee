package demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionDoubleVacancy extends RuntimeException{

    private static final String MESSAGE = "Данная вакансия уже существует";

    public NotFoundExceptionDoubleVacancy() {
        super(MESSAGE);
    }
}
