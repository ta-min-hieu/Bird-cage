package com.example.demo.Entities.production;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "blog_comments", schema = "production")
public class BlogComments implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "comment_headline")
    private String commentHeadline;
}