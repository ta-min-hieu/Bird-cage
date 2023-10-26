package com.example.demo.Repo;

import com.example.demo.Entities.dbo.BirdTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirdTypesRepo extends JpaRepository<BirdTypes, Integer> {
    @Query(value = "SELECT * FROM bird_types", nativeQuery = true)
    List<BirdTypes> get();
}