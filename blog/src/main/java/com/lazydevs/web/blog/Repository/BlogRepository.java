package com.lazydevs.web.blog.Repository;

import com.lazydevs.web.blog.Model.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long>,PagingAndSortingRepository<Blog,Long>{
    
}
