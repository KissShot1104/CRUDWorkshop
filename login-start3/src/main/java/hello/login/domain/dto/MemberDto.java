package hello.login.domain.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberDto {

    //private Long id;
    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String name;
    @NotEmpty(message = "아이디를 입력해주세요.")
    private String loginId;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
}
