package bilibili.majiang.community.service;

import bilibili.majiang.community.dto.GithubUserInfo;
import bilibili.majiang.community.mapper.GithubUserMapper;
import bilibili.majiang.community.model.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName GithubUserService
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/31 14:39
 * @Version 1.0
 */
@Service
public class GithubUserService {

    @Autowired
    private GithubUserMapper githubUserMapper;

    public String createOrUpdate(GithubUserInfo githubUserInfo) {
        String token;
        GithubUser githubUser = new GithubUser();
        if (0 != githubUserMapper.findByAccountId(githubUserInfo.getId())){
            githubUser.setAvatarUrl(githubUserInfo.getAvatarUrl());
            githubUser.setGmtModified(System.currentTimeMillis());
            githubUser.setName(githubUserInfo.getName());
            githubUser.setAccountId(String.valueOf(githubUserInfo.getId()));
            githubUserMapper.update(githubUser);
            token = githubUserMapper.getToken(String.valueOf(githubUserInfo.getId()));
        }
        else{
            githubUser.setAccountId(String.valueOf(githubUserInfo.getId()));
            githubUser.setAvatarUrl(githubUserInfo.getAvatarUrl());
            githubUser.setGmtCreated(System.currentTimeMillis());
            githubUser.setGmtModified(githubUser.getGmtCreated());
            githubUser.setName(githubUserInfo.getName());
            githubUser.setToken(String.valueOf(UUID.randomUUID()));
            githubUserMapper.insert(githubUser);
            token = githubUser.getToken();
        }
        return token;
    }

}