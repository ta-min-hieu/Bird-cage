package com.example.demo.controller;

import com.example.demo.DTO.PageDto;
import com.example.demo.Entities.production.RegularCages;
import com.example.demo.Service.RegularCagesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@Log4j2
@CrossOrigin("http://localhost:3000")
@RequestMapping("/regular-cage")
public class RegularCagesController {
    @Autowired
    RegularCagesService service;
    @GetMapping(value = {"/get"})
    public ResponseEntity<?> get(@RequestParam(name = "page", required = false) Integer page,
                                 @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(name = "cageName", required = false) String cageName,
                                 @RequestParam(name = "birdtypeId", required = false) Integer birdtypeId,
                                 @RequestParam(name = "cageId", required = false) Integer cageId) {
        if(page == null)
            page = 1;
        if(pageSize == null)
            pageSize = 1000;
        Page<RegularCages> objectPage = service.get(cageName, birdtypeId, cageId, page, pageSize);
        List<RegularCages> list = objectPage.toList();
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