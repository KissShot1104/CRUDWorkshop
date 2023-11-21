package hello.login.domain.member;

import hello.login.domain.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto){
        Member member = Member.builder()
                .name(memberDto.getName())
                .loginId(memberDto.getName())
                .password(memberDto.getPassword())
                .build();

        memberRepository.save(member);
    }

    public boolean checkDuplecateLoginId(MemberDto memberDto) {

        Optional<Member> member = memberRepository.findByLoginId(memberDto.getLoginId());

        return member.isPresent();
    }

}
