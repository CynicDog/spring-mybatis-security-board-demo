package kr.co.jhta.repository;

import kr.co.jhta.vo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface ArticleDao {

    void insertArticle(Article article);
    List<Article> findAllArticlesPaginated(Map<String, Objects> params);
    List<Article> findAllArticles();
    Article findById(int id);
    void update(Article article); // column `deleted` in the table of `Article` is to be changed as `Y`
    List<Article> findArticlesByAuthorId(int id);
}
