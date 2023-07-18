package kr.co.jhta.vo;

import lombok.Builder;

import java.util.Date;

public class Board {

    private int id;
    private String title;
    private String content;
    private int readCount;
    private int reviewCount;
    private double reviewAvgScore;
    private String deleted;
    private Date updateDate;
    private Date createDate;
    private User user;

    public Board() {
    }

    @Builder
    public Board(String title, String content, int readCount, int reviewCount, double reviewAvgScore, String deleted, Date updateDate, Date createDate, User user) {
        this.title = title;
        this.content = content;
        this.readCount = readCount;
        this.reviewCount = reviewCount;
        this.reviewAvgScore = reviewAvgScore;
        this.deleted = deleted;
        this.updateDate = updateDate;
        this.createDate = createDate;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public double getReviewAvgScore() {
        return reviewAvgScore;
    }

    public void setReviewAvgScore(double reviewAvgScore) {
        this.reviewAvgScore = reviewAvgScore;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
