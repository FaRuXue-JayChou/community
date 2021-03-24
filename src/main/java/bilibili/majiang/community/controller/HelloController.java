package bilibili.majiang.community.controller;

import bilibili.majiang.community.dto.QuestionDTO;
import bilibili.majiang.community.mapper.GithubUserMapper;
import bilibili.majiang.community.mapper.WechatUserMapper;
import bilibili.majiang.community.model.GithubUser;
import bilibili.majiang.community.model.WechatUser;
import bilibili.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private GithubUserMapper githubUserMapper;

    @Autowired
    private WechatUserMapper wechatUserMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest httpServletRequest,
                        Model model){
        Cookie[] cookies = httpServletRequest.getCookies();
        if(null != cookies && 0 < cookies.length){
            for(Cookie cookie: cookies){
                if("type".equals(cookie.getName())){
                    String type = cookie.getValue();
                    if("github".equals(type))
                        return "redirect: githubHello";
                    else if("wechat".equals(type))
                        return "redirect: wechatHello";
                }
            }
        }
        List<QuestionDTO> questionDTOList = questionService.list();
        model.addAttribute("questionDTOList", questionDTOList);
        return "index";
    }

    @GetMapping("/githubHello")
    public String githubHello(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        if(null != cookies && 0 < cookies.length){
            for(Cookie cookie: cookies){
                if("token".equals(cookie.getName())){
                    GithubUser githubUser = githubUserMapper.findByToken(cookie.getValue());
                    if(0 != githubUser.getId())
                        httpServletRequest.getSession().setAttribute("githubUser", githubUser);
                    break;
                }
            }
        }
        return "index";
    }

    @GetMapping("/wechatHello")
    public String wechatHello(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        if(null != cookies && 0 < cookies.length){
            for(Cookie cookie: cookies){
                if("token".equals(cookie.getName())){
                    WechatUser wechatUser = wechatUserMapper.findByToken(cookie.getValue());
                    if(null != wechatUser)
                        httpServletRequest.getSession().setAttribute("wechatUser", wechatUser);
                    break;
                }
            }
        }
        return "index";
    }

}