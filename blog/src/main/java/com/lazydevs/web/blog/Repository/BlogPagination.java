package com.lazydevs.web.blog.Repository;

import java.util.List;

import com.lazydevs.web.blog.Model.Blog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPagination extends PagingAndSortingRepository<Blog,Long> {
}
