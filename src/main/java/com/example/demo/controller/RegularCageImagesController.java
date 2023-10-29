package com.example.demo.controller;

import com.example.demo.DTO.PageDto;
import com.example.demo.Entities.production.RegularCageImage;
import com.example.demo.Entities.production.RegularCages;
import com.example.demo.Service.RegularCageImagesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@CrossOrigin("http://localhost:3000")
@RequestMapping("/regular-cage-images")
public class RegularCageImagesController {
    @Autowired
    RegularCageImagesService service;
    @GetMapping(value = {"/get"})
    public ResponseEntity<?> get(@RequestParam(name = "page", required = false) Integer page,
                                 @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(name = "cageId", required = false) Integer cageId) {
        if(page == null)
            page = 1;
        if(pageSize == null)
            pageSize = 1000;
        Page<RegularCageImage> objectPage = service.get(cageId, page, pageSize);
        List<RegularCageImage> list = objectPage.toList();
        PageDto response = PageDto.builder()
                .code(200)
                .message("success")
                .totalPages(objectPage.getTotalPages())
                .totalItems((int) objectPage.getTotalElements())
                .list(new ArrayList<>(list))
                .build();

        log.info("objects.toList|" + objectPage.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}