package com.example.demo.Entities.sales;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "payment", schema = "sales")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "pay_date")
    private Date payDate;
    @Column(name = "method")
    private String method;
    @Column(name = "payment_id")
    private Integer paymentId;
}