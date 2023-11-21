package hello.login.web.member;

import hello.login.domain.dto.MemberDto;
import hello.login.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private static final String ADD_MEMBER_FORM = "/members/addMemberForm";


    @GetMapping("/add")
    public String addForm(@ModelAttribute(name = "member") MemberDto memberDto) {
        return ADD_MEMBER_FORM;
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute(name = "member") MemberDto memberDto,
                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ADD_MEMBER_FORM;
        }

        if (memberService.checkDuplecateLoginId(memberDto)) {
            bindingResult.reject("duplicateLoginId", "중복된 아이디입니다.");
            return ADD_MEMBER_FORM;
        }

        memberService.save(memberDto);

        return "redirect:/";
    }

}
