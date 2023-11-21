package hello.login.domain.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MemberDto {

    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String name;
    @NotEmpty(message = "로그인아이디를 입력해주세요")
    private String loginId;
    @NotEmpty(message = "패스워드를 입력해주세요.")
    private String password;

}
