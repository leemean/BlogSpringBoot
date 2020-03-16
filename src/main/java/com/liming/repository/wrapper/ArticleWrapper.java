package com.liming.repository.wrapper;

import com.liming.entity.Article;
import com.liming.vo.ArticleVo;
import com.liming.vo.PageVo;

import java.util.List;

public interface ArticleWrapper {
    List<Article> listArticles(PageVo page);

    List<Article> listArticles(ArticleVo article, PageVo page);

    List<ArticleVo> listArchives();
}
