package kr.co.jhta.controller;

import kr.co.jhta.service.BoardService;
import kr.co.jhta.vo.Article;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/", "/main", "/home"})
public class HomeController {

    private final Logger logger = Logger.getLogger(HomeController.class);
    private final BoardService boardService;

    public HomeController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String home(Model model) {

        List<Article> mostViewedArticles = boardService.findArticlesOrderedByReadCount(3);
        List<Article> mostCommentedArticles = boardService.findArticlesOrderedByReviewCount(5);
        List<Article> mostRecentArticles = boardService.findArticlesOrderedByCreateDate(4);

        model.addAttribute("mostViewed", mostViewedArticles);
        model.addAttribute("mostCommented", mostCommentedArticles);
        model.addAttribute("mostRecent", mostRecentArticles);

        return "home";
    }
}
