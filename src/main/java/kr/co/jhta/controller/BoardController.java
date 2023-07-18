package kr.co.jhta.controller;

import kr.co.jhta.service.MvcService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final MvcService mvcService;

    public BoardController(MvcService mvcService) {
        this.mvcService = mvcService;
    }

    @GetMapping("/add")
    public String addForm() {
        return "board/add-form";
    }

    @PostMapping("/add")
    public String add() {
        return "redirect:";
    }

    @GetMapping("/recent")
    public String findTenRecentArticles() {
        return "";
    }

    @GetMapping("/detail")
    public String detail() {
        return "board/detail";
    }

    @GetMapping("/list")
    public String list() {
        return "board/list";
    }

    // TODO: handler for delete operation
    // TODO: handler for modification form request
    // TODO: handler for modification operation
}
