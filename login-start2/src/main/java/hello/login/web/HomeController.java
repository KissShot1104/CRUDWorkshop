package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.web.resolver.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Login Member member, Model model) {

        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
}