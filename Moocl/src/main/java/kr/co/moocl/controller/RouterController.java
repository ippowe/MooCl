package kr.co.moocl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RouterController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}