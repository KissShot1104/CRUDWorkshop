package hello.login.web.api;

import hello.login.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/api/{id}")
    public MemberDto exceptionHandle(@PathVariable String id) {
        if (id.equals("bad")) {
            throw new IllegalArgumentException("IllegalException");
        }
        if (id.equals("user-ex")) {
            throw new UserException("UserException");
        }
        if (id.equals("ex")) {
            throw new RuntimeException("runtimeException");
        }

        return MemberDto.builder()
                .name(id)
                .build();
    }

}
