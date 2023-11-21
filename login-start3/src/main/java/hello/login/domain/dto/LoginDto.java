package hello.login.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
