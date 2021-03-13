package bilibili.majiang.community.controller;

import bilibili.majiang.community.dto.AccessToken;
import bilibili.majiang.community.dto.GithubUser;
import bilibili.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id("b8eeca538ba2ccd88770");
        accessToken.setClient_secret("f64553f3c60292622257d183c41ebf2835b300ab");
        accessToken.setCode(code);
        accessToken.setRedirect_uri("http://localhost:8080/callback");
        accessToken.setState(state);
        //使用返回的 code 和 state 获取 access_token
        String tokenString = githubProvider.getAccessToken(accessToken);
        //使用返回的 access_token 获取 用户信息
        GithubUser githubUser = githubProvider.getGithubUser(tokenString);
        System.out.println(githubUser.getName());
        return "index";
    }

}