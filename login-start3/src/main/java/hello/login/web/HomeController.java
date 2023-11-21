package hello.login.web;

import hello.login.domain.dto.MemberDto;
import hello.login.web.resolver.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Login MemberDto memberDto, Model model) {

        if (memberDto == null) {
            return "/home";
        }

        model.addAttribute("member", memberDto);
        return "/loginHome";
    }
}