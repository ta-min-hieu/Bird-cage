package com.example.demo.Service;

import com.example.demo.Repo.CustomCageOrdersRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class CustomCageOrdersServiceImpl implements CustomCageOrdersService {
    @Autowired
    CustomCageOrdersRepository repository;

}
