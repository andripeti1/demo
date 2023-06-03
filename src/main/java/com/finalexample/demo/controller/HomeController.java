package com.finalexample.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController extends BaseController {


    @GetMapping("")
    public String getHomeView(Model model){

        if ("ADMIN".equals(extractAuthority())) {
            return ok("redirect:admin", model);
        }
        return ok("index", model);

    }

}
