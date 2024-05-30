package org.example.bigevent.config;

import org.example.bigevent.interceptors.LoginInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptors loginInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把拦截器配置进来
        //登录和注册接口不应该被拦截
        registry.addInterceptor(loginInterceptors).excludePathPatterns("/user/login","/user/register");
    }
}
