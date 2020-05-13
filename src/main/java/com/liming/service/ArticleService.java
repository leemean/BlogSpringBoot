package com.liming.service;

import com.liming.entity.Article;
import com.liming.vo.ArticleVo;
import com.liming.vo.PageVo;

import java.util.List;

public interface ArticleService {
    List<Article> listArticles(PageVo page);

    List<Article> listArticles(ArticleVo article,PageVo page);

    List<Article> findAll();

    Article getArticleById(Long id);

    Long publishArticle(Article article);

    Long createArticle(Article article);

    Long updateArticle(Article article);

    void deleteArticleById(Long id);

    List<Article> listArticlesByTag(Integer id);

    List<Article> listArticlesByCategory(Integer id);

    Article getArticleAndAddViews(Long id);

    List<Article> listHotArticles(int limit);

    List<Article> listNewArticles(int limit);

    List<ArticleVo> listArchives();
}
