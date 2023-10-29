package com.example.demo.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AddCartDto {
    private Integer productId;
    private String username;
    private String shape;
    private String material;
    private String description;
    private String birdtypeId;
    private Integer basePrice;
}
