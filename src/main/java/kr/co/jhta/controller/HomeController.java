package kr.co.jhta.controller;

import kr.co.jhta.service.MvcService;
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
    private final MvcService mvcService;

    public HomeController(MvcService mvcService) {
        this.mvcService = mvcService;
    }

    @GetMapping
    public String home(Model model) {

        List<Article> mostViewedArticles = mvcService.findArticlesOrderedByReadCount(3);
        List<Article> mostCommentedArticles = mvcService.findArticlesOrderedByReviewCount(5);
        List<Article> mostRecentArticles = mvcService.findArticlesOrderedByCreateDate(4);

        model.addAttribute("mostViewed", mostViewedArticles);
        model.addAttribute("mostCommented", mostCommentedArticles);
        model.addAttribute("mostRecent", mostRecentArticles);

        return "home";
    }
}
