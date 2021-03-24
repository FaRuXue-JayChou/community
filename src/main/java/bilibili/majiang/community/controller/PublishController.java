package bilibili.majiang.community.controller;

import bilibili.majiang.community.mapper.QuestionMapper;
import bilibili.majiang.community.model.Question;
import bilibili.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PublishController
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/18 21:41
 * @Version 1.0
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title") String title,
                            @RequestParam(name = "description") String description,
                            @RequestParam(name = "tag") String tag,
                            Model model,
                            HttpServletRequest httpServletRequest){
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        //如果有空则返回错误信息
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if(null == title || "".equals(title)){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if(null == description || "".equals(description)){
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if(null == tag || "".equals(tag)){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        question.setGmtCreated(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreated());
        //通过 session 获取用户属性
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        question.setCreator(user.getId());
        questionMapper.create(question);
        return "index";
    }

}