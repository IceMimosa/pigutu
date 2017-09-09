package com.pigutu.app.controller;

import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.utils.TuConfig;
import com.pigutu.app.utils.TuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ErrorController {
    @GetMapping("/401")
    public String a401() {
        return "redirect:http://img.pigutu.com/error.html";
    }
    @GetMapping("/404")
    public String a404() {
        return "redirect:http://img.pigutu.com/error.html";
    }
    @GetMapping("/500")
    public String a500() {
        return "redirect:http://img.pigutu.com/error.html";
    }
}
