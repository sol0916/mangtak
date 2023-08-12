package com.delivery.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/addr")
public class addrController {

    @GetMapping("/addrSearch")
    public String addrSearch() {
        return "/addr/addrSearch";
    }

    @PostMapping("searchForm")
    public String searchForm(@RequestParam("searchArea") String area,
                             Model model) {
        model.addAttribute("area", area);
        return "/addr/addrSearch";
    }

}
