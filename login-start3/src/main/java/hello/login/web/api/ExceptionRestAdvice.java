package hello.login.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionRestAdvice {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResultDto illegalException(IllegalArgumentException e) {
        return ErrorResultDto.builder()
                .code("bad")
                .msg(e.getMessage())
                .build();
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResultDto> userEx(UserException e) {
        ErrorResultDto errorResultDto = ErrorResultDto.builder()
                .code("user-ex")
                .msg(e.getMessage())
                .build();

        return new ResponseEntity<>(errorResultDto, HttpStatus.BAD_GATEWAY);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResultDto ex(Exception e) {
        return ErrorResultDto.builder()
                .code("ex")
                .msg(e.getMessage())
                .build();
    }

}
