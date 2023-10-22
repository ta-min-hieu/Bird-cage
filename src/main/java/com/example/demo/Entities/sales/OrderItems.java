package com.example.demo.Entities.sales;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "order_items", schema = "sales")
public class OrderItems implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "cage_id")
    private Integer cageId;
    @Column(name = "order_quantity")
    private Integer orderQuantity;
    @Column(name = "list_price")
    private Integer listPrice;
}