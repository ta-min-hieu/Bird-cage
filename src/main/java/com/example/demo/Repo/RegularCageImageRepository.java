package com.example.demo.Repo;

import com.example.demo.Entities.production.RegularCageImage;
import com.example.demo.Entities.production.RegularCages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularCageImageRepository extends JpaRepository<RegularCageImage, Integer> {
    @Query(value = "SELECT * FROM production.regular_cage_images " +
            "where (:cage_id is null or cage_id = :cage_id)",
            countQuery = "SELECT count(*) FROM production.regular_cage_images " +
                    "where (:cage_id is null or cage_id = :cage_id)",
            nativeQuery = true)
    Page<RegularCageImage> get(@Param("cage_id") Integer cageId, Pageable pageable);
}