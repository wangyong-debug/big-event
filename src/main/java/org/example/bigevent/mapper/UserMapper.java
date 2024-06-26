package org.example.bigevent.mapper;

import org.apache.ibatis.annotations.*;
import org.example.bigevent.pojo.User;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User findByUsername(String username) ;

    @Insert("insert into user(username, password, create_time, update_time) " +
            "values(#{username}, #{password}, now(), now())")
    void add(@Param("username") String username, @Param("password") String password);

    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = #{updateTime} where id = #{id}")
    void update(User user);

    @Update("update user set user_pic = #{avatar}, update_time = now() where id = #{id}")
    void updateAvatar(@Param("avatar") String avatar, @Param("id") Integer id);

    @Update("update user set password = #{password}, update_time = now() where id = #{id}")
    void updatePwd(@Param("password") String password, @Param("id") Integer userId);
}
