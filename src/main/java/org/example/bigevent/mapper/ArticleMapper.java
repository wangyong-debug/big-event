package org.example.bigevent.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.bigevent.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void create(Article article);

    //注解写动态sql会非常麻烦，使用可以使用映射去写
    //创建与Java mapper中路径系统的路径 用/分割层级
    List<Article> list(@Param("userId") Integer userId,
                       @Param("categoryId") Integer categoryId,
                       @Param("state") String state);
}
