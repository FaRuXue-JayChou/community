package bilibili.majiang.community.controller;

import bilibili.majiang.community.dto.QuestionDTO;
import bilibili.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName QuestionController
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/31 10:47
 * @Version 1.0
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        questionService.increaseViewCount(id);
        QuestionDTO questionDTO = questionService.findById(id);
        model.addAttribute("questionDTO", questionDTO);
        return "question";
    }

}