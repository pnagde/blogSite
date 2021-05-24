package com.lazydevs.web.blog.Service;

import java.util.ArrayList;
import java.util.List;

import com.lazydevs.web.blog.Model.Blog;
import com.lazydevs.web.blog.Repository.BlogPagination;
import com.lazydevs.web.blog.Repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogService {


    @Autowired
    private BlogPagination blogPagination;

    public Page<Blog> findAll(Pageable pageable){
        return blogPagination.findAll(pageable);
    }

    public boolean save(Blog blog){
        boolean saved=false;
        if(blog!=null){
            blogPagination.save(blog);
            saved=true;
        }
        return saved;
    }

    public Blog findById(long id){
        return blogPagination.findById(id).get();
    }
}
