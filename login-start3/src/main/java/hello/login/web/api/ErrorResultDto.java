package hello.login.web.api;


import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ErrorResultDto {
    private String code;
    private String msg;
}
