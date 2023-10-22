package com.example.demo.Service;

import com.example.demo.Entities.production.RegularCages;
import org.springframework.data.domain.Page;

public interface RegularCagesService {
    Page<RegularCages> get(String cageName, Integer birdtypeId, Integer cageId, int pageNo, int pageSize);
}