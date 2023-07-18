package kr.co.jhta.repository;

import kr.co.jhta.vo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface ArticleDao {

    void insertArticle(Article article);
    List<Article> findAllArticlesPaginated(@Param("begin") int begin, @Param("end") int end);
    List<Article> findAllArticles();
    Article findById(int id);
    void update(Article article); // column `deleted` in the table of `Article` is to be changed as `Y`
    List<Article> findArticlesByAuthorId(int id);
    int getTotalRows();
    List<Article> findArticlesOrderedByReadCount(@Param("limit") int limit);
    List<Article> findArticlesOrderedByReviewCount(@Param("limit") int limit);
    List<Article> findArticlesOrderedByCreateDate(@Param("limit") int limit);
}
