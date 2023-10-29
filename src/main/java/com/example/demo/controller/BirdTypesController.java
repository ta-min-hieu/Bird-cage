package com.example.demo.controller;

import com.example.demo.DTO.PageDto;
import com.example.demo.Entities.dbo.BirdTypes;
import com.example.demo.Service.BirdTypesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@Log4j2
@CrossOrigin("http://localhost:3000")
@RequestMapping("/bird-types")
public class BirdTypesController {
    @Autowired
    BirdTypesService service;
    @GetMapping(value = {"/get"})
    public ResponseEntity<?> get() {
        List<BirdTypes> list = service.get();
        log.info("listlist|" + list.toString());
        PageDto response = PageDto.builder()
                .code(200)
                .message("success")
                .list(new ArrayList<>(list)).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}