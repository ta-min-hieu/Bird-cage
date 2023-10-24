package com.example.demo.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class OrdersDto {
    private Integer orderId;
    private Integer customerId;
    private String orderNote;
    private String orderStatus;
    private Date orderDate;
}
