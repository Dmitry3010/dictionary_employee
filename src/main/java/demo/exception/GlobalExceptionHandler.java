package demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import demo.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class, DuplicateException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public ErrorResponse handleNotFoundException(RuntimeException ex, WebRequest request) {
        return new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
    }
}
