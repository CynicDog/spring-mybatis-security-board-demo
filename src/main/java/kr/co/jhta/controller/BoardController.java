package kr.co.jhta.controller;

import kr.co.jhta.response.ArticlesPaginated;
import kr.co.jhta.security.model.SecurityUser;
import kr.co.jhta.service.MvcService;
import kr.co.jhta.util.FetchType;
import kr.co.jhta.vo.Article;
import org.jboss.logging.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class BoardController {

    private final Logger logger = Logger.getLogger(BoardController.class);
    private final MvcService mvcService;

    public BoardController(MvcService mvcService) {
        this.mvcService = mvcService;
    }

    @GetMapping("/add")
    public String addForm() {
        return "article/add-form";
    }

    @PostMapping("/add")
    public String add(@AuthenticationPrincipal SecurityUser user,
                      @RequestParam("title") String title,
                      @RequestParam("content") String content) {

        mvcService.insertArticle(title, content, user.getUser());

        return "redirect:/article/list";
    }

    @GetMapping("/recent")
    public String findTenRecentArticles() {
        return "";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = "id", required = true) int articleId,
                         Model model) {

        Article article = mvcService.findArticleById(articleId, FetchType.EAGER, FetchType.LAZY);

        mvcService.incrementArticleViewCount(article);
        model.addAttribute("article", article);

        return "article/detail";
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(value = "rows", required = false, defaultValue = "10") int rows,
                       Model model) {

        ArticlesPaginated articlesPaginated = mvcService.findAllArticlesPaginated(page, rows, FetchType.EAGER, FetchType.EAGER);

        model.addAttribute("response", articlesPaginated);

        return "article/list";
    }

    // TODO: handler for delete operation
    // TODO: handler for modification form request
    // TODO: handler for modification operation
}
