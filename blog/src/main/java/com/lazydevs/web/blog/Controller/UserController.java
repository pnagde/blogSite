package com.lazydevs.web.blog.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.lazydevs.web.blog.Model.Blog;
import com.lazydevs.web.blog.Model.User;
import com.lazydevs.web.blog.Service.BlogService;
import com.lazydevs.web.blog.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService uService;

    @Autowired
    private BlogService bService;
    
    @RequestMapping("/")
    public String index(HttpSession session,Model model){
        boolean isValid=false;
        session.setAttribute("isValid", isValid);
        List<Blog> blogs=bService.findAll();
        model.addAttribute("blogs", blogs);
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping(path = "/check",method = RequestMethod.POST)
    public String check(@RequestParam(name = "email") String email ,@RequestParam(name = "password") String password,HttpServletRequest servletRequest ,Model model){
        boolean isValid=false;
        isValid=uService.check(email, password);
        if(isValid){
            servletRequest.getSession().setAttribute("isValid", isValid);
            servletRequest.getSession().setAttribute("email", email);
            User currentUser=uService.getUser(email);
            System.out.println(currentUser.getEmailId()+"");
        if(currentUser!=null){
            servletRequest.getSession().setAttribute("currentUser", currentUser);
        }
        return "redirect:/";
        }
        return "redirect:/login";
    }
    @RequestMapping("/signup")
    public String register(){
        return "signup";
    }
    @RequestMapping("/view/{id}")
    public String view(@PathVariable(name = "id") String id ,Model model){
        long bid=Long.parseLong(id);
        System.out.println(bid);
        Blog blog=bService.findById(bid);
        if(blog!=null){
            model.addAttribute("blog", blog);
        }
        return "view";
    }
    @RequestMapping("/create")
    public String createBlog(HttpServletRequest sRequest){
        User user=(User)sRequest.getSession().getAttribute("currentUser");
        long Uid=user.getUserId();
        sRequest.getSession().setAttribute("uid", Uid);
        return "createPost";
    }
    @RequestMapping("/createblog")
    public String create(HttpServletRequest sRequest,Blog blog){
        long uid=(long)sRequest.getSession().getAttribute("uid");
        User user=uService.findByUserId(uid);
        if(blog!=null){
            blog.setByUserId(user.getUsername());
            bService.save(blog);
        }
        return "index";
    }
    @RequestMapping("/save")
    public String save(User user, Model model){
        boolean saved=false;
        if(user!=null){
            saved=uService.save(user);
        }
        model.addAttribute("saved", saved);
        return "login";
    }
}