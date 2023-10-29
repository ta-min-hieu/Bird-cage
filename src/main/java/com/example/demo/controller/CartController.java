package com.example.demo.controller;

import com.example.demo.DTO.ApiResponse;
import com.example.demo.DTO.PageDto;
import com.example.demo.Entities.dbo.Cart;
import com.example.demo.Repo.CartRepository;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService service;
    @Autowired
    CartRepository repository;
    @GetMapping(value = {"/get"})
    public ResponseEntity<?> get(@RequestParam(name = "page", required = false) Integer page,
                                 @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(name = "username", required = false) String username,
                                 @RequestParam(name = "status", required = false) Integer status) {
        if(page == null)
            page = 1;
        if(pageSize == null)
            pageSize = 1000;
        Page<Cart> objectPage = service.get(username, page, pageSize, status);
        List<Cart> list = objectPage.toList();
        log.info("listlist|" + objectPage.toList().toString());
//        PageDto response = PageDto.builder()
//                .code(200)
//                .message("success")
//                .totalPages(objectPage.getTotalPages())
//                .totalItems((int) objectPage.getTotalElements())
//                .list(Collections.singletonList(list)).build();

        return new ResponseEntity<>(list, HttpStatus.OK);
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
                                       @RequestParam(name = "username", required = false) String username,
                                       @RequestParam(name = "shape", required = false) String shape,
                                       @RequestParam(name = "material", required = false) String material,
                                       @RequestParam(name = "description", required = false) String description,
                                       @RequestParam(name = "birdtypeId", required = false) String birdtypeId,
                                       @RequestParam(name = "basePrice", required = false) Integer basePrice) {
        Cart object = new Cart();
        object.setUsername(username);
        object.setProductId(productId);
        if(productId == null) {
            object.setStatus(2);
            object.setShape(shape);
            object.setMaterial(material);
            object.setDescription(description);
            object.setPrice(service.processPriceOrder(shape, material, basePrice));
            object.setBirdtypeId(birdtypeId);
        }
        log.info("Object save|" + object);
        service.addToCart(object);
        PageDto response = PageDto.builder()
                .code(200)
                .message("success")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/pay"})
    public ResponseEntity<?> pay(@RequestParam(name = "username", required = false) String username,
                                 @RequestParam(name = "status", required = false) Integer status) {
        String paymentUrl = service.processPay(username, status);
        ApiResponse response = new ApiResponse();
        response.setCode("200");
        response.setMessage("success");
        response.setData(paymentUrl);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/bill"})
    public ResponseEntity<?> bill(@RequestParam(name = "username", required = false) String username,
                                  @RequestParam(name = "status", required = false) Integer status) {
        Page<Cart> cartPage = service.bill(username, status);
        PageDto response = PageDto.builder()
                .code(200)
                .message("success")
                .totalPages(cartPage.getTotalPages())
                .totalItems((int) cartPage.getTotalElements())
                .list(new ArrayList<>(cartPage.toList()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/bill-all"})
    public ResponseEntity<?> billAll(@RequestParam(name = "username", required = false) String username) {
        List<Cart> list = repository.getAllBill();
        PageDto response = PageDto.builder()
                .code(200)
                .message(repository.sumPriceBillAll())
                .list(new ArrayList<>(list))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/price-by-id"})
    public ResponseEntity<?> billAllPRICE(@RequestParam(name = "username", required = false) String username) {
        PageDto response = PageDto.builder()
                .code(200)
                .message(repository.sumPriceById(username))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/remove-all-customize"})
    public ResponseEntity<?> removeAllCustomize(@RequestParam(name = "username", required = false) String username) {
        repository.removeAllOrderCustomize(username);
        PageDto response = PageDto.builder()
                .code(200)
                .message("success")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}