package com.example.demo.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class CustomCageOrdersDto {
    private Integer orderId;
    private Integer customerId;
    private String orderNote;
    private String orderStatus;
    private Date orderDate;
    private Date shippedDate;
    private Integer staffId;
    private Integer estimateTime;
    private Integer totalPrice;
    private String cageColor;
    private String cageShape;
    private String cageMaterial;
    private String cageSpokes;
    private String cageFeet;
    private String cagePerch;
    private String cageCups;
//    private List<>
}
