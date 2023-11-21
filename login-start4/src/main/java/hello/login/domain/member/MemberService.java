package hello.login.domain.member;

import hello.login.domain.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save(MemberDto memberDto) {
        memberRepository.save(MemberConverter.memberDtoToMember(memberDto));
    }


    public Optional<MemberDto> findById(Long id) {
        return memberRepository.findById(id)
                .map(MemberConverter::memberToMemberDto);
    }

    public List<MemberDto> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberConverter::memberToMemberDto)
                .collect(Collectors.toList());
    }



}
