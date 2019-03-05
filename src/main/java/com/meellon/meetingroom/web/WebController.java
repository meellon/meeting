package com.meellon.meetingroom.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/web")
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}