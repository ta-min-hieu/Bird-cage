package com.example.demo.Entities.production;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "cage_spokes", schema = "production")
public class CageSpokes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spokes_id")
    private Integer spokesId;
    @Column(name = "spokes")
    private String spokes;
}