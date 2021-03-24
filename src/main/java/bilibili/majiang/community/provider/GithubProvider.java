package bilibili.majiang.community.provider;

import bilibili.majiang.community.dto.ForGithubToken;
import bilibili.majiang.community.dto.GithubUserInfo;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class GithubProvider {

    public String getAccessToken(ForGithubToken forGithubToken){
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(forGithubToken));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()){
            return response.body().string().split("&")[0].split("=")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUserInfo getGithubUser(String tokenString){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/githubUser")
                .header("Authorization", "token " + tokenString)
                .build();
        try (Response response = client.newCall(request).execute()){
            return JSON.parseObject(response.body().string(), GithubUserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}