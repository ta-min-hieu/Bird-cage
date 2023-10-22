package com.example.demo.Entities.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feet_juntions", schema = "dbo")
public class FeetJuntions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feet_id")
    private Integer feetId;
    @Column(name = "birdtype_id")
    private Integer birdtypeId;
    @Column(name = "price")
    private Integer price;
    @Column(name = "time")
    private Integer time;
}
