package bilibili.majiang.community.controller;

import bilibili.majiang.community.dto.AccessToken;
import bilibili.majiang.community.dto.GithubUser;
import bilibili.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest httpServletRequest){
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(client_id);
        accessToken.setClient_secret(client_secret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirect_uri);
        accessToken.setState(state);
        //使用返回的 code 和 state 获取 access_token
        String tokenString = githubProvider.getAccessToken(accessToken);
        //使用返回的 access_token 获取 用户信息
        GithubUser githubUser = githubProvider.getGithubUser(tokenString);
        if(null != githubUser){
            httpServletRequest.getSession().setAttribute("githubUser", githubUser);
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

}