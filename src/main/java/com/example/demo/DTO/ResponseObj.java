package com.example.demo.DTO;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseObj {
    private int code;
    private String message;
    private Object data;
}
