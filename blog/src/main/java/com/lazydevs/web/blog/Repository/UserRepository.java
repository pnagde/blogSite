package com.lazydevs.web.blog.Repository;

import com.lazydevs.web.blog.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    

    @Query("select user from User user where user.emailId=?1")
    public User findbyEmail(String email);
}
