package bilibili.majiang.community.inteceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebConfig
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/31 9:20
 * @Version 1.0
 */
@Configuration
//@EnableWebMvc
@Component
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInteceptor sessionInteceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInteceptor).addPathPatterns("/");
    }

}