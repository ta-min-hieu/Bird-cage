package com.example.demo.Entities.sales;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders", schema = "sales")
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_note")
    private String orderNote;
    @Column(name = "shipped_date")
    private Date shippedDate;
    @Column(name = "staff_id")
    private Integer staffId;
    @Column(name = "total_amount")
    private Float totalAmount;
}