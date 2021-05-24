package com.lazydevs.web.blog.Repository;

import com.lazydevs.web.blog.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    

    @Query("select user from User user where user.emailId=?1")
    public User findbyEmail(String email);

    @Transactional
    @Modifying()
    @Query(value = "update User u set u.username=?1,u.emailId=?2,u.address=?3,u.city=?4,u.state=?5,u.zipcode=?6 where u.userId=?7")
    public void update(String username,String emailId,String address,String city,String state,int zipcode,long id);
}
