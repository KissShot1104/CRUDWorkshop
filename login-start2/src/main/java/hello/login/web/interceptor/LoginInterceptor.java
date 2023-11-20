package hello.login.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String redirectURL = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/login?redirectURI=" + redirectURL);
            return false;
        }

        return true;
    }
}
