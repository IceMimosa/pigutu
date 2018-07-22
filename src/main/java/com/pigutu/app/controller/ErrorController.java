package com.pigutu.app.controller;

import com.pigutu.app.entity.ResponseReturn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 错误页面重定向
 */
@Controller
public class ErrorController {
    @GetMapping("/401")
    @ResponseBody
    public ResponseReturn a401() {
        return ResponseReturn.error(401);
    }
    @GetMapping("/404")
    @ResponseBody
    public ResponseReturn a404() {
        return ResponseReturn.error(404);
    }
  /*  @GetMapping("/500")
    @ResponseBody
    public ResponseReturn a500() {
        return ResponseReturn.error(500);
    }*/
}
