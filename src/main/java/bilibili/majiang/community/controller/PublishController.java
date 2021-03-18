package bilibili.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName PublishController
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/18 21:41
 * @Version 1.0
 */
@Controller
public class PublishController {

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

}