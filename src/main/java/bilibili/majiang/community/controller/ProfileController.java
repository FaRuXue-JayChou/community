package bilibili.majiang.community.controller;

import bilibili.majiang.community.dto.NavPropDTO;
import bilibili.majiang.community.dto.QuestionDTO;
import bilibili.majiang.community.model.GithubUser;
import bilibili.majiang.community.service.NavPropService;
import bilibili.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName ProfileController
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/30 15:34
 * @Version 1.0
 */
@Controller
public class ProfileController {

    @Autowired
    private NavPropService navPropService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profileDispatcher(@PathVariable(name = "action") String action,
                                    Model model,
                                    HttpServletRequest httpServletRequest,
                                    @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum){
        GithubUser githubUser = (GithubUser) httpServletRequest.getSession().getAttribute("user");
        Integer pageSize = 3;
        NavPropDTO navPropDTO = navPropService.caculateConfigByUser(githubUser, pageNum, pageSize);
        model.addAttribute("navPropDTO", navPropDTO);
        List<QuestionDTO> questionDTOList = questionService.listByUser(githubUser, navPropDTO.getPageNum(), pageSize);
        model.addAttribute("questionDTOList", questionDTOList);
        if("questions".equals(action)){
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我提问的");
        }
        else if("replies".equals(action)){
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "回复我的");
        }
        return "profile";
    }

}