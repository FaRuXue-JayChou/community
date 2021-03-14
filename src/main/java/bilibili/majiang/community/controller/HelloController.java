package bilibili.majiang.community.controller;

import bilibili.majiang.community.mapper.UserMapper;
import bilibili.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String hello(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        if(null != cookies && 0 < cookies.length){
            for(Cookie cookie: cookies){
                if("token".equals(cookie.getName())){
                    User user = userMapper.findByToken(cookie.getValue());
                    if(0 != user.getId())
                        httpServletRequest.getSession().setAttribute("user", user);
                    break;
                }
            }
        }
        return "index";
    }

}