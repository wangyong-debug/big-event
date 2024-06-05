package org.example.bigevent.controller;

import org.example.bigevent.pojo.Article;
import org.example.bigevent.pojo.PageBean;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article) {

        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pbArticle= articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pbArticle);
    }
}
