package com.pigutu.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FeedBackController {
    @GetMapping("/feedback")
    public String feedBack(){
        return "feedBack";
    }
    @PostMapping("submitfeedback")
    @ResponseBody
    public void submitFeedBack(String content){
        System.out.println(content);
    }
}
