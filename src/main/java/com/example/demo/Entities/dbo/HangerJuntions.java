package com.example.demo.Entities.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hanger_juntions", schema = "dbo")
public class HangerJuntions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hanger_id")
    private Integer hangerId;
    @Column(name = "birdtype_id")
    private Integer birdtypeId;
    @Column(name = "price")
    private Integer price;
    @Column(name = "time")
    private Integer time;
}
