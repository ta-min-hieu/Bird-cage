package com.example.demo.Entities.production;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "cage_perch", schema = "production")
public class CagePerch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perch_id")
    private Integer perchId;
    @Column(name = "perch")
    private String perch;
}