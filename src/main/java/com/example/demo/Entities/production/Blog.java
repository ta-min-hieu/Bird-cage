package com.example.demo.Entities.production;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "blog", schema = "production")
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Integer blogId;
    @Column(name = "blog_content")
    private String blogContent;
    @Column(name = "comment_id")
    private Integer commentId;
}