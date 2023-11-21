package hello.login.domain.member;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    private Long id;
    private String name;
    private String loginId;
    private String password;
}
