package kr.co.jhta.controller;

import kr.co.jhta.security.model.SecurityUser;
import kr.co.jhta.service.MvcService;
import kr.co.jhta.util.FetchType;
import kr.co.jhta.vo.Article;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final MvcService mvcService;

    public UserController(MvcService mvcService) {
        this.mvcService = mvcService;
    }

    @GetMapping("/add")
    public String addForm() {
        return "user/add-form";
    }

    @PostMapping("/register")
    public String add(@RequestParam("email") String email,
                      @RequestParam("password") String password) {

        mvcService.insertUser(email, password);

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @GetMapping("/my-page")
    public String myPage(@AuthenticationPrincipal SecurityUser user, Model model) {

        List<Article> articles = mvcService.findArticlesByAuthorId(user.getUser().getId(), FetchType.EAGER, FetchType.EAGER);

        model.addAttribute("articles", articles);
        model.addAttribute("user", user);

        return "user/my-page";
    }

}
