package bilibili.majiang.community.inteceptor;

import bilibili.majiang.community.mapper.GithubUserMapper;
import bilibili.majiang.community.model.GithubUser;
import bilibili.majiang.community.model.GithubUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SessionInteceptor
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/31 9:34
 * @Version 1.0
 */
@Component
public class SessionInteceptor implements HandlerInterceptor {

    @Autowired
    private GithubUserMapper githubUserMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(null != cookies && 0 < cookies.length){
            for(Cookie cookie: cookies){
                if("token".equals(cookie.getName())){
                    GithubUserExample githubUserExample = new GithubUserExample();
                    githubUserExample.createCriteria().andTokenEqualTo(cookie.getValue());
                    GithubUser githubUser = githubUserMapper.selectByExample(githubUserExample).get(0);
                    if(0 != githubUser.getId())
                        request.getSession().setAttribute("user", githubUser);
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}