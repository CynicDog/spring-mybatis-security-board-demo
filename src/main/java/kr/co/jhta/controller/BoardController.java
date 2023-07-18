package kr.co.jhta.controller;

import kr.co.jhta.response.ArticlesPaginated;
import kr.co.jhta.security.model.SecurityUser;
import kr.co.jhta.service.BoardService;
import kr.co.jhta.util.FetchType;
import kr.co.jhta.vo.Article;
import kr.co.jhta.vo.Comment;
import org.jboss.logging.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
public class BoardController {

    private final Logger logger = Logger.getLogger(BoardController.class);
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/add")
    public String addForm() {
        return "article/add-form";
    }

    @PostMapping("/add")
    public String add(@AuthenticationPrincipal SecurityUser user,
                      @RequestParam("title") String title,
                      @RequestParam("content") String content) {

        boardService.insertArticle(title, content, user.getUser());

        return "redirect:/article/list";
    }

    @GetMapping("/recent")
    public String findTenRecentArticles() {
        return "";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = "id", required = true) int articleId,
                         Model model) {

        Article article = boardService.findArticleById(articleId, FetchType.EAGER, FetchType.LAZY);

        boardService.incrementArticleViewCount(article);
        model.addAttribute("article", article);

        List<Comment> comments = boardService.findCommentsByArticleId(articleId, FetchType.EAGER, FetchType.LAZY);
        model.addAttribute("comments", comments);

        return "article/detail";
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(value = "rows", required = false, defaultValue = "10") int rows,
                       Model model) {

        ArticlesPaginated articlesPaginated = boardService.findAllArticlesPaginated(page, rows, FetchType.EAGER, FetchType.EAGER);

        model.addAttribute("response", articlesPaginated);

        return "article/list";
    }

    @PostMapping("/leave-comment")
    public String comment(@RequestParam(value = "article-id") int articleId,
                          @RequestParam(value = "content") String content,
                          @AuthenticationPrincipal SecurityUser user,
                          Model model) {

        boardService.insertComment(content, user.getUser(), articleId);

        return "redirect:/article/detail?id=" + articleId;
    }

    // TODO: handler for delete operation
    // TODO: handler for modification form request
    // TODO: handler for modification operation
}
