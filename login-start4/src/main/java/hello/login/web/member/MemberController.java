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

    @GetMapping("/add")
    public String addForm(@ModelAttribute(value = "member") MemberDto memberDto) {
        return "/members/addMemberForm";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute(value = "member") MemberDto memberDto,
                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        memberService.save(memberDto);

        return "redirect:/";
    }

}
