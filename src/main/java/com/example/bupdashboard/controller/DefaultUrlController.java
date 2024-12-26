package com.example.bupdashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultUrlController {
    @RequestMapping("/")
    public String redirectToDashboards() {
        return "redirect:/dashboards";
    }
}
