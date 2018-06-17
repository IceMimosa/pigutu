package com.pigutu.app.controller;

import com.pigutu.app.mapper.FeedbackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/***
 * 反馈
 */
@Controller
public class FeedBackController {
    @Autowired
    FeedbackDao feedbackDao;

    @GetMapping("/feedback")
    public String feedBack() {
        return "feedback/feedBack";
    }

    @PostMapping("submitfeedback")
    public String submitFeedBack(String content) {
      //  feedBackDao.insert(content);
        return "feedback/feedBackSucc";
    }
}
