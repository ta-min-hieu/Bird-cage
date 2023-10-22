package com.example.demo.DTO;

import lombok.Data;

@Data
public class ApiResponse {
    private String code;
    private String message;
    private String data;
}
