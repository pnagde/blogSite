package com.lazydevs.web.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/signup")
    public String register(){
        return "signup";
    }
    @RequestMapping("/view")
    public String view(){
        return "view";
    }
    @RequestMapping("/create")
    public String createBlog(){
        return "createPost";
    }
}