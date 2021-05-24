package com.lazydevs.web.blog.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.lazydevs.web.blog.Model.Blog;
import com.lazydevs.web.blog.Model.State;
import com.lazydevs.web.blog.Model.User;
import com.lazydevs.web.blog.Repository.BlogPagination;
import com.lazydevs.web.blog.Repository.StateRepository;
import com.lazydevs.web.blog.Service.BlogService;
import com.lazydevs.web.blog.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UserController {
    
    @Value("${uplaod.path}")
	private String uploadFolder;

    @Autowired
    private UserService uService;

    @Autowired
    private BlogService bService;

    @Autowired
    private StateRepository stateRepository;

    @RequestMapping("/")
    public String index(HttpSession session,Model model){
        org.springframework.data.domain.Pageable firstPageWithFourElement=PageRequest.of(0,3);
        Page<Blog> blogs=bService.findAll(firstPageWithFourElement);
        List<Blog> blog=blogs.getContent();
        model.addAttribute("blogs", blog);
        return "index";
    }
    @RequestMapping("/next/{page}")
    public String next(HttpSession session,Model model,@PathVariable("page") int page){
        org.springframework.data.domain.Pageable firstPageWithFourElement=PageRequest.of(page-1, 3);
        Page<Blog> blogs=bService.findAll(firstPageWithFourElement);
        List<Blog> blog=blogs.getContent();
        model.addAttribute("blogs", blog);
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
    public String register(Model model){
        List<State> states=stateRepository.findAll();
        model.addAttribute("states", states);
        return "signup";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession(false);

      session = request.getSession(false);
      if(session != null) {
          session.invalidate();
      }

      for(Cookie cookie : request.getCookies()) {
          cookie.setMaxAge(0);
      }
        return "redirect:/login";
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
    public String create(HttpServletRequest sRequest,Blog blog,@RequestParam(name = "file") MultipartFile file ,Model model) throws IOException
    {
        long uid=(long)sRequest.getSession().getAttribute("uid");
        User user=uService.findByUserId(uid);
        String fileName=file.getOriginalFilename();
        Path path=Paths.get(uploadFolder+fileName);
        try {
            if(fileName==null || fileName.contains("..")){
                model.addAttribute("invalid", "sorry");
            }
        InputStream inputStream=file.getInputStream();
        Files.copy(inputStream,path,StandardCopyOption.REPLACE_EXISTING);
        }
         catch (Exception e) {
            e.getStackTrace();
        }
        
        if(blog!=null){
            blog.setImageName(fileName);
            blog.setImageFile(path.toString());
            System.out.println(path.toString());
            blog.setByUserId(user.getUsername());
            bService.save(blog);
            }
        return "redirect:/";
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
    @RequestMapping("/profile")
    public String profile(HttpServletRequest sRequest,Model view){
        User user=(User)sRequest.getSession().getAttribute("currentUser");
        List<State> states=stateRepository.findAll();
        view.addAttribute("states", states);
        view.addAttribute("user", user);
        return "profile";
    }
    @RequestMapping("/saveUser")
    public String saveProfile(HttpServletRequest sRequest,Model view,@Validated User user){
        System.out.println(user.getState());
        User u=(User)sRequest.getSession().getAttribute("currentUser");
        uService.updateUser(user.getUsername(),user.getEmailId(),user.getAddress(),user.getCity()
        ,user.getState(),user.getZipcode(),u.getUserId());
        System.out.println(user.getState());
        view.addAttribute("user", user);
        sRequest.getSession().setAttribute("currentUser", user);
        return "redirect:/profile";
    }
}