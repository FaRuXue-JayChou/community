package bilibili.majiang.community.controller;

import bilibili.majiang.community.dto.ForWechatToken;
import bilibili.majiang.community.dto.WechatToken;
import bilibili.majiang.community.mapper.WechatUserMapper;
import bilibili.majiang.community.model.WechatUser;
import bilibili.majiang.community.provider.WechatProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ClassName WechatAuthorizeController
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/24 10:31
 * @Version 1.0
 */
@Controller
public class WechatAuthorizeController {

    @Autowired
    private WechatUserMapper wechatUserMapper;

    @Autowired
    private WechatProvider wechatProvider;

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.secret}")
    private String secret;

    @GetMapping("/wechatCallback")
    public String callback(@RequestParam(name = "code") String code,
                           HttpServletResponse httpServletResponse){
        ForWechatToken forWechatToken = new ForWechatToken();
        forWechatToken.setAppid(appid);
        forWechatToken.setSecret(secret);
        forWechatToken.setCode(code);
        WechatToken wechatToken = wechatProvider.getToken(forWechatToken);
        WechatUser wechatUser = wechatProvider.getWeChatUser(wechatToken);
        if(null != wechatUser.getUnionId() && "".equals(wechatUser.getUnionId())){
            if(null != wechatUserMapper.findByUnionId(wechatUser.getUnionId()))
                return "/";
            wechatUser.setToken(UUID.randomUUID().toString());
            wechatUser.setGmtCreated(System.currentTimeMillis());
            wechatUser.setGmtModified(wechatUser.getGmtCreated());
            wechatUserMapper.create(wechatUser);
            httpServletResponse.addCookie(new Cookie("type", "wechat"));
            httpServletResponse.addCookie(new Cookie("token", wechatUser.getToken()));
        }
        return "/";
    }

}