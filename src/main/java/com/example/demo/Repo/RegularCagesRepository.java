package com.example.demo.Repo;

import com.example.demo.Entities.production.RegularCages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RegularCagesRepository extends JpaRepository<RegularCages, Integer> {
    @Query(value = "SELECT * FROM production.regular_cages " +
            "where (:cage_name is null or cage_name LIKE CONCAT('%', :cage_name, '%')) " +
            "and (:birdtype_id is null or birdtype_id = :birdtype_id) " +
            "and (:cage_id is null or cage_id = :cage_id)",
            countQuery = "SELECT count(*) FROM production.regular_cages " +
                    "where (:cage_name is null or cage_name LIKE CONCAT('%', :cage_name, '%')) " +
                    "and (:birdtype_id is null or birdtype_id = :birdtype_id)" +
                    "and (:cage_id is null or cage_id = :cage_id)",
            nativeQuery = true)
    Page<RegularCages> get(@Param("cage_name") String cageName, @Param("birdtype_id") Integer birdtypeId,
                           @Param("cage_id") Integer cageId, Pageable pageable);

    @Modifying
    @Query(value = "update production.regular_cages set quantity = quantity - 1 " +
            "where cage_id = :cage_id", nativeQuery = true)
    void updateQuantity(@Param(value = "cage_id") Integer cageId);
}