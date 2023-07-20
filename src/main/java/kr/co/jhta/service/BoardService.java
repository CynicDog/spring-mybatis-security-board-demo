package kr.co.jhta.service;

import kr.co.jhta.repository.*;
import kr.co.jhta.repository.jdbc.UserRepository;
import kr.co.jhta.response.ArticlesPaginated;
import kr.co.jhta.util.FetchType;
import kr.co.jhta.util.Pagination;
import kr.co.jhta.vo.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final ArticleDao articleDao;
    private final PasswordEncoder passwordEncoder;
    private final CommentDao commentDao;
    private final UserRepository userRepository;
    private final FollowsRequestDao followsRequestDao;
    private final FollowsDao followsDao;

    public BoardService(UserDao userDao, RoleDao roleDao, ArticleDao articleDao, PasswordEncoder passwordEncoder, CommentDao commentDao, UserRepository userRepository, FollowsRequestDao followsRequestDao, FollowsDao followsDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.articleDao = articleDao;
        this.passwordEncoder = passwordEncoder;
        this.commentDao = commentDao;
        this.userRepository = userRepository;
        this.followsRequestDao = followsRequestDao;
        this.followsDao = followsDao;
    }

    public void insertUser(String email, String password) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        String roleName = "ROLE_USER";
        user.addRole(roleName);
        userDao.insertUser(user);

        Role role = new Role(user, roleName);
        roleDao.insertRole(role);
    }

    public void insertArticle(String title, String content, User author) {

        Article article = new Article(title, content, author);
        articleDao.insertArticle(article);
    }

    public List<Article> findAllArticles(FetchType arg1) {

        List<Article> articles = articleDao.findAllArticles();

        List<Article> articlesPopulated = articles.stream().map(article -> {
            if (article.getAuthor() != null && FetchType.EAGER.equals(arg1)) {
                article.setAuthor(userDao.findById(article.getAuthor().getId()));
            }

            return article;
        }).collect(Collectors.toList());

        return articlesPopulated;
    }

    public List<Article> findArticlesByAuthorId(int id, FetchType arg1, FetchType arg2) {

        List<Article> articles = articleDao.findArticlesByAuthorId(id);

        List<Article> articlesPopulated = articles.stream().map(article -> {
            if (article.getAuthor() != null && FetchType.EAGER.equals(arg1)) {

                User author = userDao.findById(article.getAuthor().getId());

                if (FetchType.EAGER.equals(arg2)) {
                    List<Role> roles = roleDao.findByUserId(author.getId());

                    if (roles.size() > 0) {
                        roles.forEach(role -> {
                            author.addRole(role.getRoleName());
                        });
                    }
                }
                article.setAuthor(author);
            }
            return article;
        }).collect(Collectors.toList());

        return articlesPopulated;
    }


    public ArticlesPaginated findAllArticlesPaginated(int page, int rows, FetchType arg1, FetchType arg2) {

        ArticlesPaginated articlesPaginated = new ArticlesPaginated();

        int totalRows = articleDao.getTotalRows();
        Pagination pagination = new Pagination(rows, 7, page, totalRows);

        articlesPaginated.setPagination(pagination);

        int begin = pagination.getBegin();
        int end = pagination.getEnd();

        List<Article> articles = articleDao.findAllArticlesPaginated(begin, end);

        List<Article> articlesPopulated = articles.stream().map(article -> {
            if (article.getAuthor() != null && FetchType.EAGER.equals(arg1)) {

                User author = userDao.findById(article.getAuthor().getId());

                if (FetchType.EAGER.equals(arg2)) {
                    List<Role> roles = roleDao.findByUserId(author.getId());

                    if (roles.size() > 0) {
                        roles.forEach(role -> {
                            author.addRole(role.getRoleName());
                        });
                    }
                }
                article.setAuthor(author);
            }
            return article;
        }).collect(Collectors.toList());

        articlesPaginated.setArticles(articlesPopulated);

        return articlesPaginated;
    }

    public Article findArticleById(int articleId, FetchType arg1, FetchType arg2) {

        Article article = articleDao.findById(articleId);

        if (article.getAuthor() != null && FetchType.EAGER.equals(arg1)) {
            User author = userDao.findById(article.getAuthor().getId());

            if (FetchType.EAGER.equals(arg2)) {
                List<Role> roles = roleDao.findByUserId(author.getId());

                if (roles.size() > 0) {
                    roles.forEach(role -> {
                        author.addRole(role.getRoleName());
                    });
                }
            }
            article.setAuthor(author);
        }

        return article;
    }

    public void incrementArticleViewCount(Article article) {

        article.setReadCount(article.getReadCount() + 1);
        article.setUpdateDate(new Date());

        articleDao.update(article);
    }

    public void insertComment(String content, User user, int articleId) {

        Comment comment = new Comment(content, user, articleDao.findById(articleId));

        Article article = articleDao.findById(articleId);
        article.setReviewCount(article.getReviewCount() + 1);
        articleDao.update(article);

        commentDao.insertComment(comment);
    }

    public List<Comment> findCommentsByArticleId(int articleId, FetchType arg1, FetchType arg2) {

        List<Comment> comments = commentDao.findCommentByArticleId(articleId);

        List<Comment> commentPopulated = comments.stream().map(comment -> {
            if (comment.getUser() != null && FetchType.EAGER.equals(arg1)) {
                comment.setUser(userDao.findById(comment.getUser().getId()));
            }
            return comment;
        }).map(comment -> {
            if (comment.getArticle() != null && FetchType.EAGER.equals(arg2)) {
                comment.setArticle(articleDao.findById(comment.getArticle().getId()));
            }
            return comment;
        }).collect(Collectors.toList());

        return commentPopulated;
    }

    public List<Article> findArticlesOrderedByReadCount(int limit) {

        return articleDao.findArticlesOrderedByReadCount(limit);
    }

    public List<Article> findArticlesOrderedByReviewCount(int limit) {

        return articleDao.findArticlesOrderedByReviewCount(limit);
    }

    public List<Article> findArticlesOrderedByCreateDate(int limit) {

        return articleDao.findArticlesOrderedByCreateDate(limit);
    }

    public void queueRequest(int senderId, int recipientId) {

        if (followsRequestDao.checkIfExists(senderId, recipientId)) {
            throw new RuntimeException("Already requested.");
        }

        FollowsRequest followsRequest = new FollowsRequest(senderId, recipientId);
        followsRequestDao.insertFollowsRequest(followsRequest);
    }

    public List<FollowsRequest> findArrivedRequests(int recipientId) {

        return followsRequestDao.findRequestsByRecipientId(recipientId);
    }

    public List<FollowsRequest> findSentRequests(int senderId) {

        return followsRequestDao.findRequestsBySenderId(senderId);
    }

    public void acceptRequest(int senderId, int id) {
        // TODO
    }

    public User login(String email, String password) {
        return null;
    }

    public User findUserById(int userId) {
        return userDao.findById(userId);
    }

    public void modifyUser(User user) {
    }

    public void createBoard(ArticleDao articleDao) {
    }

    public List<Article> findBoardsPaginated(Map<String, Objects> params) {
        return null;
    }

    public List<User> jdbc_findAllUsers() {

        return (List) userRepository.findAll();
    }
}


