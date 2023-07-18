package kr.co.jhta.controller;

import kr.co.jhta.security.model.SecurityUser;
import kr.co.jhta.service.MvcService;
import kr.co.jhta.util.FetchType;
import kr.co.jhta.vo.Article;
import kr.co.jhta.vo.User;
import org.jboss.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String detail() {
        return "article/detail";
    }

    @GetMapping("/list")
    public String list(Model model) {

        List<Article> articles = mvcService.findAllArticles(FetchType.EAGER);

        model.addAttribute("articles", articles);

        return "article/list";
    }

    // TODO: handler for delete operation
    // TODO: handler for modification form request
    // TODO: handler for modification operation
}
