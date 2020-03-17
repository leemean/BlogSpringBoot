package com.liming.service.impl;

import com.liming.common.util.UserUtils;
import com.liming.entity.Article;
import com.liming.entity.Category;
import com.liming.entity.Tag;
import com.liming.entity.User;
import com.liming.repository.ArticleRepository;
import com.liming.service.ArticleService;
import com.liming.vo.ArticleVo;
import com.liming.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> listArticles(PageVo page) {
        return articleRepository.listArticles(page);
    }

    @Override
    public List<Article> listArticles(ArticleVo article, PageVo page) {
        return articleRepository.listArticles(article,page);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.getOne(id);
    }

    @Override
    @Transactional
    public Long publishArticle(Article article) {
        if(null != article.getId()){
            return this.updateArticle(article);
        }else{
            return this.saveArticle(article);
        }
    }

    @Override
    @Transactional
    public Long saveArticle(Article article) {
        User currentUser = UserUtils.getCurrentUser();

        if(null != currentUser){
            article.setAuthor(currentUser);
        }
        article.setCreateTime(new Date());
        article.setTop(Article.Article_Common);
        return articleRepository.save(article).getId();
    }

    @Override
    @Transactional
    public Long updateArticle(Article article) {
        Article old = articleRepository.getOne(article.getId());
        old.setTitle(article.getTitle());
        old.setSummary(article.getSummary());
        old.setBody(article.getBody());
        old.setCategory(article.getCategory());
        old.setTags(article.getTags());
        return old.getId();
    }

    @Override
    @Transactional
    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> listArticlesByTag(Integer id) {
//        Tag t = new Tag();
////        t.setId(id);
////        return articleRepository.findByTags(t);
        return null;
    }

    @Override
    public List<Article> listArticlesByCategory(Integer id) {
        Category category = new Category();
        category.setId(id);
        return articleRepository.findByCategory(category);
    }

    @Override
    @Transactional
    public Article getArticleAndAddViews(Long id) {
        int count = 1;
        Article article = articleRepository.getOne(id);
        article.setViewCounts(article.getViewCounts() + count);
        return article;
    }

    @Override
    public List<Article> listHotArticles(int limit) {
        return articleRepository.findOrderByViewsAndLimit(limit);
    }

    @Override
    public List<Article> listNewArticles(int limit) {
        return articleRepository.findOrderByCreateTimeAndLimit(limit);
    }

    @Override
    public List<ArticleVo> listArchives() {
        return articleRepository.listArchives();
    }
}
