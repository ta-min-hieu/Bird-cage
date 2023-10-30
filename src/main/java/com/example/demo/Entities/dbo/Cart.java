package com.example.demo.Entities.dbo;

import com.example.demo.Entities.production.RegularCages;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cart", schema = "dbo")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "bill_id")
    private Integer billId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "username")
    private String username;
    @Column(name = "status")
    private Integer status;
    @Column(name = "date_pay")
    private Date datePay;
    @Column(name = "shape")
    private String shape;
    @Column(name = "material")
    private String material;
    @Column(name = "description")
    private String description;
    @Column(name = "birdtypeId")
    private String birdtypeId;
    @Column(name = "price")
    private Integer price;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "expected_date")
    private String expectedDate;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "cage_id", insertable = false, updatable = false)
    private RegularCages regularCages;


}
