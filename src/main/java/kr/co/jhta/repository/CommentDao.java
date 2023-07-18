package kr.co.jhta.repository;

import kr.co.jhta.vo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {

    public void insertComment(Comment comment);
    public List<Comment> findCommentByArticleId(int articleId);
}
