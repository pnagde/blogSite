package com.lazydevs.web.blog.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long blogId;

    @Column(name = "title")
    private String title;

    @Column(name = "description" ,length = 2000000)
    private String description;
    
    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "postBy")
    private String byUserId;

    public Blog() {
    }

    public Blog(long blogId, String title, String description, String imageUrl, String byUserId) {
        this.blogId = blogId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.byUserId = byUserId;
    }

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getByUserId() {
        return byUserId;
    }

    public void setByUserId(String byUserId) {
        this.byUserId = byUserId;
    }

    
}
