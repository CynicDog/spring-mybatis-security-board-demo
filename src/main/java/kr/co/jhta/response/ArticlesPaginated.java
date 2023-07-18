package kr.co.jhta.response;

import kr.co.jhta.util.Pagination;
import kr.co.jhta.vo.Article;

import java.util.List;

public class ArticlesPaginated {

    private Pagination pagination;
    private List<Article> articles;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
