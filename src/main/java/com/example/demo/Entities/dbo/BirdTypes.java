package com.example.demo.Entities.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bird_types", schema = "dbo")
public class BirdTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birdtype_id")
    private Integer birdtypeId;
    @Column(name = "bird_name")
    private String birdName;
    @Column(name = "base_price")
    private Integer basePrice;
    @Column(name = "base_time")
    private Integer baseTime;
    @Column(name = "img")
    private String img;
}
