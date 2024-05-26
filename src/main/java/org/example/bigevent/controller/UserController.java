package org.example.bigevent.controller;

import org.example.bigevent.pojo.Result;
import org.example.bigevent.pojo.User;
import org.example.bigevent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\s{5,16}$") String username,
                           @Pattern(regexp = "^\\s{5,16}$") String password
    ) {

        User u = userService.findByUsername(username);
        if (u == null){
            //注册
            userService.register(username,password);
            System.out.println(Result.success());
            return Result.success();
        }else{
            //返回对应错误信息
            System.out.println( Result.error("用户名已被占用"));
            return Result.error("用户名已被占用");
        }
    }
}
