package hello.login.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/api/{id}")
    public UserDto apiHandler(@PathVariable String id) {
        if (id.equals("bad")) {
            throw new IllegalArgumentException("illegal!");
        }

        if (id.equals("user-ex")) {
            throw new UserException("UserException!");
        }

        if (id.equals("ex")) {
            throw new RuntimeException("Ex!!");
        }

        return new UserDto(id, "Hello " + id);
    }


    @Data
    @AllArgsConstructor
    static class UserDto {
        private String id;
        private String msg;
    }

}
