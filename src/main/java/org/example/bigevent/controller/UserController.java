package org.example.bigevent.controller;

import org.example.bigevent.pojo.Result;
import org.example.bigevent.pojo.User;
import org.example.bigevent.service.UserService;
import org.example.bigevent.utils.JwtUtil;
import org.example.bigevent.utils.Md5Util;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{3,16}$") String username,
                           @Pattern(regexp = "^\\S{3,16}$") String password
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

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{3,16}$") String username,
                                @Pattern(regexp = "^\\S{3,16}$") String password){
        //根据用户名查询用户
        User loginUser = userService.findByUsername(username);
        if (loginUser == null){
            return Result.error("用户名错误");
        }
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> findByUsername(){

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam("avatar") @URL String avatar){
        userService.updateAvatar(avatar);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //校验参数
        String oldPassword = params.get("old_pwd");
        String newPassword = params.get("new_pwd");
        String rePassword = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPassword) || !StringUtils.hasLength(newPassword) || !StringUtils.hasLength(rePassword)){
            return Result.error("缺少必要参数");
        }
        Map<String, Object> map= ThreadLocalUtil.get();
        String userName = (String) map.get("username");
        User user = userService.findByUsername(userName);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPassword))){
            return Result.error("原密码填写不正确");
        }
        if (!newPassword.equals(rePassword)){
            return Result.error("两次填写的新密码不一样");
        }
        userService.updatePwd(newPassword);
        return Result.success();
    }

}
