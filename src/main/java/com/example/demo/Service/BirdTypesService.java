package com.example.demo.Service;

import com.example.demo.Entities.dbo.BirdTypes;
import com.example.demo.Repo.BirdTypesRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Log4j2
@Transactional
public class BirdTypesService {
    @Autowired
    BirdTypesRepo repository;
    @Autowired
    private RestTemplate restTemplate;
    public List<BirdTypes> get() {
        return repository.get();
    }
}
