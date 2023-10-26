package com.example.demo.controller;

import com.example.demo.DTO.ApiResponse;
import com.example.demo.DTO.PageDto;
import com.example.demo.Entities.dbo.Cart;
import com.example.demo.Service.CartService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService service;
    @GetMapping(value = {"/get"})
    public ResponseEntity<?> get(@RequestParam(name = "page", required = false) Integer page,
                                 @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(name = "username", required = false) String username) {
        if(page == null)
            page = 1;
        if(pageSize == null)
            pageSize = 1000;
        Page<Cart> objectPage = service.get(username, page, pageSize);
        List<Cart> list = objectPage.toList();
        log.info("listlist|" + objectPage.toList().toString());
        PageDto response = PageDto.builder()
                .code(200)
                .message("success")
                .totalPages(objectPage.getTotalPages())
                .totalItems((int) objectPage.getTotalElements())
                .list(Collections.singletonList(list)).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/remove-one"})
    public ResponseEntity<?> removeOne(@RequestParam(name = "productId", required = false) Integer productId,
                                       @RequestParam(name = "username", required = false) String username) {
        service.removeOne(username, productId);
        PageDto response = PageDto.builder()
                .code(200)
                .message("success")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/add-to-cart"})
    public ResponseEntity<?> addToCart(@RequestParam(name = "productId", required = false) Integer productId,
                                       @RequestParam(name = "username", required = false) String username) {
        Cart object = new Cart();
        object.setUsername(username);
        object.setProductId(productId);
        service.addToCart(object);
        PageDto response = PageDto.builder()
                .code(200)
                .message("success")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/pay"})
    public ResponseEntity<?> pay(@RequestParam(name = "username", required = false) String username) {
        String paymentUrl = service.processPay(username);
//        PageDto response = PageDto.builder()
//                .code(200)
//                .message("success")
//                .build();
        ApiResponse response = new ApiResponse();
        response.setCode("200");
        response.setMessage("success");
        response.setData(paymentUrl);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/bill"})
    public ResponseEntity<?> bill(@RequestParam(name = "username", required = false) String username) {
        Page<Cart> cartPage = service.bill(username);
        PageDto response = PageDto.builder()
                .code(200)
                .message("success")
                .totalPages(cartPage.getTotalPages())
                .totalItems((int) cartPage.getTotalElements())
                .list(Collections.singletonList(cartPage.toList()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}