package kr.co.jhta.vo;

import org.apache.ibatis.type.Alias;

@Alias("Comment")
public class Comment {

    private int id;
    private String content;
    private User user;
    private Article article;

    private int up;
    private int down;

    public Comment() {
    }

    public Comment(String content, User user, Article article) {
        this.content = content;
        this.user = user;
        this.article = article;
    }

    public Comment(User user, Article article) {
        this.user = user;
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }
}
