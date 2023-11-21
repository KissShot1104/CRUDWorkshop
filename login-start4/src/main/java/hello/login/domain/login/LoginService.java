package hello.login.domain.login;

import hello.login.domain.dto.LoginDto;
import hello.login.domain.dto.MemberDto;
import hello.login.domain.member.MemberConverter;
import hello.login.domain.member.MemberRepository;
import hello.login.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public Optional<MemberDto> login(LoginDto loginDto) {
        return memberRepository.findByLoginId(loginDto.getLoginId()).stream()
                .filter(m -> m.getPassword().equals(loginDto.getPassword()))
                .findAny()
                .map(MemberConverter::memberToMemberDto);
    }
}
