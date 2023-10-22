package com.example.demo.Service;

import com.example.demo.Entities.production.RegularCages;
import com.example.demo.Repo.RegularCagesRepository;
import com.example.demo.common.Helper;
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
public class RegularCagesServiceImpl implements RegularCagesService {
    @Autowired
    RegularCagesRepository repository;
    @Override
    public Page<RegularCages> get(String cageName, Integer birdtypeId, Integer cageId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return repository.get(Helper.processStringSearch(cageName), birdtypeId, cageId, pageable);
    }
}
