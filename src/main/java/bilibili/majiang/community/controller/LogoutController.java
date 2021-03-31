package bilibili.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LogoutController
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/31 14:53
 * @Version 1.0
 */
@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse){
        httpServletRequest.getSession().invalidate();
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        return "redirect:/";
    }

}