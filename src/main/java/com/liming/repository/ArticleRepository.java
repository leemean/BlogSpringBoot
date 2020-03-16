package com.liming.repository;

import com.liming.entity.Article;
import com.liming.entity.Category;
import com.liming.entity.Tag;
import com.liming.repository.wrapper.ArticleWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long>, ArticleWrapper {
    //List<Article> findByTags(Tag tag);

    List<Article> findByCategory(Category category);

    @Query(value = "select * from article order by view_counts desc limit :limit", nativeQuery = true)
    List<Article> findOrderByViewsAndLimit(@Param("limit") int limit);

    @Query(value = "select * from article order by create_time desc limit :limit", nativeQuery = true)
    List<Article> findOrderByCreateTimeAndLimit(@Param("limit") int limit);
}
