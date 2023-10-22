package com.example.demo.DTO;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseObjs {
    private int code;
    private String message;
    private List<Object> data;
}
