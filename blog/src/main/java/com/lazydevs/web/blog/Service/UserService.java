package com.lazydevs.web.blog.Service;

import com.lazydevs.web.blog.Model.User;
import com.lazydevs.web.blog.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public boolean save(User user){
        boolean saved=false;
        if(user!=null){
            repository.save(user);
            saved=true;
        }
        return saved;
    }
    public boolean check(String email, String pass){
        boolean isValid=false;
        if(email!=null && pass!=null){
            User user=repository.findbyEmail(email);
            if(user.getEmailId().equals(email) && user.getPassword().equals(pass)){
                isValid=true;
            }
        }
        return isValid;
    }
    public User getUser(String email){
        User user=repository.findbyEmail(email);
        return user;
    } 
    public User findByUserId(long id){
        return repository.findById(id).get();
    }
}
