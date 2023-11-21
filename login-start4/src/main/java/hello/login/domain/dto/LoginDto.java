package hello.login.domain.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class LoginDto {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
