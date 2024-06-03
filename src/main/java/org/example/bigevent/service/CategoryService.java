package org.example.bigevent.service;

import org.example.bigevent.pojo.Category;
import org.example.bigevent.pojo.Result;

import java.util.List;

public interface CategoryService {

    //新增文章分类
    void add(Category category);

    //查询文章列表
    List<Category> list();

    //根据id查询文章分类详情
    Category findById(int id);

    //更新文章分类
    void update(Category category);
}
