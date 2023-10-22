package com.example.demo.Entities.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spoke_juntions", schema = "dbo")
public class SpokeJuntions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spoke_id")
    private Integer spokeId;
    @Column(name = "birdtype_id")
    private Integer birdtypeId;
    @Column(name = "price")
    private Integer price;
    @Column(name = "time")
    private Integer time;
}
