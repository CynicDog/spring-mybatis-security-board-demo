package kr.co.jhta.controller;

import kr.co.jhta.security.model.SecurityUser;
import kr.co.jhta.service.BoardService;
import kr.co.jhta.util.FetchType;
import kr.co.jhta.vo.Article;
import kr.co.jhta.vo.FollowsRequest;
import kr.co.jhta.vo.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final BoardService boardService;

    public UserController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/add")
    public String addForm() {
        return "user/add-form";
    }

    @PostMapping("/register")
    public String add(@RequestParam("email") String email,
                      @RequestParam("password") String password) {

        boardService.insertUser(email, password);

        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @GetMapping("/my-page")
    public String myPage(@AuthenticationPrincipal SecurityUser user, Model model) {

        List<Article> articles = boardService.findArticlesByAuthorId(user.getUser().getId(), FetchType.EAGER, FetchType.EAGER);
        model.addAttribute("articles", articles);
        model.addAttribute("user", user);

        // sent requests
        List<FollowsRequest> requestsSent = boardService.findSentRequests(user.getUser().getId());
        List<User> recipients = requestsSent.stream()
                .filter(request -> FollowsRequest.RequestStatus.PENDING.equals(request.getStatus()))
                .map(request -> boardService.findUserById(request.getRecipientId()))
                .collect(Collectors.toList());
        model.addAttribute("recipients", recipients);

        // arrived requests
        List<FollowsRequest> requestsArrived = boardService.findArrivedRequests(user.getUser().getId());
        List<User> senders = requestsArrived.stream()
                .filter(request -> FollowsRequest.RequestStatus.PENDING.equals(request.getStatus()))
                .map(request -> boardService.findUserById(request.getSenderId()))
                .collect(Collectors.toList());
        model.addAttribute("senders", senders);

        return "user/my-page";
    }

    @GetMapping("/jdbc/all-users")
    @ResponseBody
    public List<User> jdbcUsers() {
        // http GET http://localhost:8082/user/jdbc/all-users

        return boardService.jdbc_findAllUsers();
    }
}
