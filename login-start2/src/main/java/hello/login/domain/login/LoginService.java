package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Optional<Member> login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId).stream().
                filter(m -> m.getPassword().equals(password))
                .findFirst();
    }
}
