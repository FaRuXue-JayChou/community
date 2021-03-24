package bilibili.majiang.community.provider;

import bilibili.majiang.community.dto.ForWechatToken;
import bilibili.majiang.community.dto.WechatToken;
import bilibili.majiang.community.model.WechatUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName WechatProvider
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/24 10:34
 * @Version 1.0
 */
@Component
public class WechatProvider {

    public WechatToken getToken(ForWechatToken forWechatToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + forWechatToken.getAppid()
                        + "&secret=" + forWechatToken.getSecret() + "&code=" + forWechatToken.getCode()
                        + "&grant_type=authorization_code")
                .build();
        try (Response response = client.newCall(request).execute()){
            System.out.println("responseBody:" + response.body().string());
            return JSON.parseObject(response.body().string(), WechatToken.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WechatUser getWeChatUser(WechatToken wechatToken){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/sns/userinfo?access_token=" + wechatToken.getAccessToken()
                + "&openid=" + wechatToken.getOpenId()).build();
        try(Response response = okHttpClient.newCall(request).execute()){
            return JSON.parseObject(response.body().string(), WechatUser.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}