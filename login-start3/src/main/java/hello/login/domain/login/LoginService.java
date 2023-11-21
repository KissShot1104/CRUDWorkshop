package hello.login.domain.login;

import hello.login.domain.dto.LoginDto;
import hello.login.domain.dto.MemberDto;
import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Optional<MemberDto> login(LoginDto loginDto) {
        Optional<Member> member = memberRepository.findByLoginId(loginDto.getLoginId()).stream()
                .filter(m -> m.getPassword().equals(loginDto.getPassword()))
                .findFirst();

        if (member.isEmpty()) {
            return Optional.ofNullable(null);
        }

        MemberDto memberDto = MemberDto.builder()
                .name(member.get().getName())
                .loginId(member.get().getLoginId())
                .password(member.get().getPassword())
                .build();


        return Optional.of(memberDto);
    }
}
