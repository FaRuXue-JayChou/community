package bilibili.majiang.community.controller;

import bilibili.majiang.community.dto.ForGithubToken;
import bilibili.majiang.community.dto.GithubUserInfo;
import bilibili.majiang.community.mapper.UserMapper;
import bilibili.majiang.community.model.GithubUser;
import bilibili.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse httpServletResponse){
        ForGithubToken forGithubToken = new ForGithubToken();
        forGithubToken.setClient_id(client_id);
        forGithubToken.setClient_secret(client_secret);
        forGithubToken.setCode(code);
        forGithubToken.setRedirect_uri(redirect_uri);
        forGithubToken.setState(state);
        //使用返回的 code 和 state 获取 access_token
        String tokenString = githubProvider.getAccessToken(forGithubToken);
        //使用返回的 access_token 获取 用户信息
        GithubUserInfo githubUserInfo = githubProvider.getGithubUser(tokenString);
        if(0 != githubUserInfo.getId()){
            if(0 != userMapper.findByAccountId(githubUserInfo.getId()))
                return "/";
            GithubUser githubUser = new GithubUser();
            githubUser.setAccountId(String.valueOf(githubUserInfo.getId()));
            githubUser.setAvatarUrl(githubUserInfo.getAvatarUrl());
            githubUser.setGmtCreated(System.currentTimeMillis());
            githubUser.setGmtModified(githubUser.getGmtCreated());
            githubUser.setName(githubUserInfo.getName());
            githubUser.setToken(String.valueOf(UUID.randomUUID()));
            userMapper.insert(githubUser);
            httpServletResponse.addCookie(new Cookie("token", githubUser.getToken()));
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

}