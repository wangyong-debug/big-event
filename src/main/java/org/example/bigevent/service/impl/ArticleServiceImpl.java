package org.example.bigevent.service.impl;

import org.example.bigevent.mapper.ArticleMapper;
import org.example.bigevent.pojo.Article;
import org.example.bigevent.service.ArticleService;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        //字段赋值
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        articleMapper.create(article);
    }
}
