package kr.co.jhta.service;

import kr.co.jhta.repository.ArticleDao;
import kr.co.jhta.repository.RoleDao;
import kr.co.jhta.repository.UserDao;
import kr.co.jhta.util.FetchType;
import kr.co.jhta.vo.Article;
import kr.co.jhta.vo.Role;
import kr.co.jhta.vo.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MvcService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final ArticleDao articleDao;
    private final PasswordEncoder passwordEncoder;

    public MvcService(UserDao userDao, RoleDao roleDao, ArticleDao articleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.articleDao = articleDao;
        this.passwordEncoder = passwordEncoder;
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

        List<Article> articlesPopulated =
                articles.stream()
                        .map(article -> {
                            if (article.getAuthor() != null && FetchType.EAGER.equals(arg1)) {
                                article.setAuthor(userDao.findById(article.getAuthor().getId()));
                            }

                            return article;
                        })
                        .collect(Collectors.toList());

        return articlesPopulated;
    }

    public List<Article> findArticlesByAuthorId(int id, FetchType arg1) {

        List<Article> articles = articleDao.findArticlesByAuthorId(id);

        List<Article> articlesPopulated =
                articles.stream()
                        .map(article -> {
                            if (article.getAuthor() != null && FetchType.EAGER.equals(arg1)) {
                                article.setAuthor(userDao.findById(article.getAuthor().getId()));
                            }
                            return article;
                        })
                        .collect(Collectors.toList());

        return articlesPopulated;
    }

    public User login(String email, String password) {
        return null;
    }

    public User findUserDetailById(int userId) {
        return null;
    }

    public void modifyUser(User user) {
    }

    public void createBoard(ArticleDao articleDao) {
    }
    public List<Article> findBoardsPaginated(Map<String, Objects> params) {
        return null;
    }
    public void updateBoard(Article article) {
    }
}


