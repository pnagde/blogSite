package com.lazydevs.web.blog.Service;

import java.util.List;

import com.lazydevs.web.blog.Model.Blog;
import com.lazydevs.web.blog.Repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogRepository bRepository;

    public boolean save(Blog blog){
        boolean saved=false;
        if(blog!=null){
            bRepository.save(blog);
            saved=true;
        }
        return saved;
    }

    public List<Blog> findAll(){
        return bRepository.findAll();
    }

    public Blog findById(long id){
        return bRepository.getOne(id); 
    }
}
