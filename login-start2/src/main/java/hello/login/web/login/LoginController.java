package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(LoginForm loginForm) {
        return "/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated LoginForm loginForm,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURI,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/login/loginForm";
        }

        Optional<Member> member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (member.isEmpty()) {
            return "/login/loginForm";
        }

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(SessionConst.LOGIN_MEMBER, member.get());

        return "redirect:" + redirectURI;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
