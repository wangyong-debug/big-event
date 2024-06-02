package org.example.bigevent.service;

import org.example.bigevent.pojo.User;

public interface UserService {

    //根据查询
    User findByUsername(String username);
    //注册
    void register(String username, String password);
    //更新
    void update(User user);
    //更新用户头像
    void updateAvatar(String avatar);
}
