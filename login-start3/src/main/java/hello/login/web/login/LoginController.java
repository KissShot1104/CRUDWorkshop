package hello.login.web.login;

import hello.login.domain.dto.LoginDto;
import hello.login.domain.dto.MemberDto;
import hello.login.domain.login.LoginService;
import hello.login.web.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private static final String LOGIN_FORM = "/login/loginForm";


    @GetMapping("/login")
    public String loginForm(@ModelAttribute(name = "loginForm")LoginDto loginDto) {
        return LOGIN_FORM;
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute(name = "loginForm")LoginDto loginDto,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURI,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return LOGIN_FORM;
        }

        Optional<MemberDto> memberDto = loginService.login(loginDto);

        if (memberDto.isEmpty()) {
            bindingResult.reject("LoginFail", "로그인에 실패했습니다.");
            return LOGIN_FORM;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, memberDto.get());

        return "redirect:" + redirectURI;
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
