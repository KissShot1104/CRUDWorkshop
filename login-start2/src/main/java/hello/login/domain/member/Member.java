package hello.login.domain.member;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    Long id;
    @NotEmpty
    String name;
    @NotEmpty
    String loginId;
    @NotEmpty
    String password;
}
