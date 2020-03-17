package com.liming.controller;

import com.liming.common.constant.Base;
import com.liming.common.constant.ResultCode;
import com.liming.common.result.Result;
import com.liming.entity.Article;
import com.liming.service.ArticleService;
import com.liming.vo.ArticleVo;
import com.liming.vo.PageVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public Result listArticles(ArticleVo article, PageVo page){
        List<Article> articles = articleService.listArticles(article,page);
        return Result.success(articles);
    }

    @PostMapping("/publish")
    @RequiresAuthentication
    public Result saveArticle(@Validated @RequestBody Article article){
        Long id = articleService.publishArticle(article);
        Result r = Result.success();
        r.simple().put("articleId",id);
        return r;
    }

    @PostMapping("/update")
    @RequiresRoles(Base.ROLE_ADMIN)
    public Result updateArticle(@RequestBody Article article){
        Result r = new Result();
        if(null == article.getId()){
            r.setResultCode(ResultCode.USER_NOT_EXIST);
            return r;
        }
        Long id = articleService.updateArticle(article);
        r.setResultCode(ResultCode.SUCCESS);
        r.simple().put("articleId",id);
        return r;
    }

    @PostMapping("/delete/{id}")
    @RequiresRoles(Base.ROLE_ADMIN)
    public Result deleteArticle(@PathVariable("id") Long id){
        Result r = new Result();
        if(null == id){
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
        }

        articleService.deleteArticleById(id);
        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }
}
