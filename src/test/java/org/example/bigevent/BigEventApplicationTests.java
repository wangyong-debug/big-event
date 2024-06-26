package org.example.bigevent;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BigEventApplicationTests {

    @Test
    void jwtTest() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        //生成jwt的代码
        String token = JWT.create()
                .withClaim("user", claims)//载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60))//过期时间
                .sign(Algorithm.HMAC256("wangfugui"));//指定算法,配置密钥

        System.out.println(token);
    }

    @Test
    void jwtTestParse(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MTcwNTg3MDl9.ChF4gMHqQ183bL17AqSciY3KQ2C0CFY_6K8H0M8goac";
        JWTVerifier jwtVerifier= JWT.require(Algorithm.HMAC256("wangfugui")).build();
        DecodedJWT decodedJWT= jwtVerifier.verify(token);//验证token，生成一个解析后的jwt对象
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }

    @Test
    void ThreadLocalTest(){
        //开启一个threadLocal对象
        ThreadLocal threadLocal = new ThreadLocal();

        //开启两个线程
        new Thread(()->{
            threadLocal.set("萧炎");
            System.out.println(Thread.currentThread().getName()+ ":"+ threadLocal.get());

        },"蓝色").start();

        new Thread(()->{
            threadLocal.set("药尘");
            System.out.println(Thread.currentThread().getName()+ ":"+ threadLocal.get());

        },"绿色").start();

    }

}
