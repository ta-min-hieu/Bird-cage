package com.example.demo.Entities.production;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "cage_reviews", schema = "production")
public class CageReviews implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;
    @Column(name = "review_content")
    private String reviewContent;
    @Column(name = "review_headline")
    private String reviewHeadline;
    @Column(name = "rate")
    private Float rate;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "review_date")
    private Date reviewDate;
}