package com.example.demo.DTO;

import com.example.demo.Entities.dbo.Cart;
import com.example.demo.Entities.production.RegularCages;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PageCartDto {
    private int code;
    private String message;
//    private int page;
//    private int pageSize;
    private Integer totalPages;
    private int totalItems;
    private List<Cart> cartList;
    private List<RegularCages> regularCagesList;
}
