package org.example.bigevent.interceptors;

import org.example.bigevent.pojo.Result;
import org.example.bigevent.utils.JwtUtil;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LoginInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        //验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);

            //把业务数据存在threadLocal中
            //threadLocal:线程局部变量，同一个 ThreadLocal 所包含的对象，在不同的 Thread 中有不同的副本
            ThreadLocalUtil.set(claims);

            //放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            //不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空threadLocal中的数据 防止内存泄漏
        ThreadLocalUtil.remove();
    }
}
