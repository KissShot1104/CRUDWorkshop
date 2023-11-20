package hello.login.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalException(IllegalArgumentException e) {
        return new ErrorResult("bad", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userException(UserException e) {
        ErrorResult result = new ErrorResult("user-ex", e.getMessage());

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult ex(Exception e) {
        return new ErrorResult("ex", e.getMessage());
    }

}
