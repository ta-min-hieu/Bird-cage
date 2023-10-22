package com.example.demo.Entities.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "material_juntions", schema = "dbo")
public class MaterialJuntions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private Integer materialId;
    @Column(name = "birdtype_id")
    private Integer birdtypeId;
    @Column(name = "price")
    private Integer price;
    @Column(name = "time")
    private Integer time;
}
