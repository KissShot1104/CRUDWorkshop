package hello.login.domain.member;

import hello.login.domain.dto.MemberDto;

public class MemberConverter {


    public static MemberDto memberToMemberDto(Member member) {
        return MemberDto.builder()
                .name(member.getName())
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .build();
    }

    public static Member memberDtoToMember(MemberDto memberDto) {
        return Member.builder()
                .name(memberDto.getName())
                .loginId(memberDto.getLoginId())
                .password(memberDto.getPassword())
                .build();
    }

}
