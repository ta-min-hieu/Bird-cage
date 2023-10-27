package com.example.demo.Service;

import com.example.demo.Entities.production.RegularCageImage;
import com.example.demo.Repo.RegularCageImageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class RegularCageImagesService {
    @Autowired
    RegularCageImageRepository repository;
    public Page<RegularCageImage> get(Integer cageId, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return repository.get(cageId, pageable);
    }
}