package bilibili.majiang.community.controller;

import bilibili.majiang.community.dto.NavPropDTO;
import bilibili.majiang.community.dto.QuestionDTO;
import bilibili.majiang.community.mapper.GithubUserMapper;
import bilibili.majiang.community.model.GithubUser;
import bilibili.majiang.community.service.NavPropService;
import bilibili.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private GithubUserMapper githubUserMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NavPropService navPropService;

    @GetMapping("/")
    public String hello(HttpServletRequest httpServletRequest,
                        Model model,
                        @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum){
        Cookie[] cookies = httpServletRequest.getCookies();
        if(null != cookies && 0 < cookies.length){
            for(Cookie cookie: cookies){
                if("token".equals(cookie.getName())){
                    GithubUser githubUser = githubUserMapper.findByToken(cookie.getValue());
                    if(0 != githubUser.getId())
                        httpServletRequest.getSession().setAttribute("user", githubUser);
                    break;
                }
            }
        }
        Integer pageSize = 3;
        NavPropDTO navPropDTO = navPropService.caculateConfig(pageNum, pageSize);
        model.addAttribute("navPropDTO", navPropDTO);
        List<QuestionDTO> questionDTOList = questionService.list(navPropDTO.getPageNum(), pageSize);
        model.addAttribute("questionDTOList", questionDTOList);
        return "index";
    }

}