package com.example.demo.Entities.sales;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "custom_cage_orders", schema = "sales")
public class CustomCageOrders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "order_note")
    private String orderNote;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "shipped_date")
    private Date shippedDate;
    @Column(name = "staff_id")
    private Integer staffId;
    @Column(name = "estimate_time")
    private Integer estimateTime;
    @Column(name = "total_price")
    private Integer totalPrice;
    @Column(name = "cage_color")
    private String cageColor;
    @Column(name = "cage_shape")
    private String cageShape;
    @Column(name = "cage_material")
    private String cageMaterial;
    @Column(name = "cage_spokes")
    private String cageSpokes;
    @Column(name = "cage_feet")
    private String cageFeet;
    @Column(name = "cage_perch")
    private String cagePerch;
    @Column(name = "cage_cups")
    private String cageCups;
}