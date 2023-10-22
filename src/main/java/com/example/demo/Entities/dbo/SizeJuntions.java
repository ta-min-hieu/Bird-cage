package com.example.demo.Entities.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "size_juntions", schema = "dbo")
public class SizeJuntions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Integer sizeId;
    @Column(name = "birdtype_id")
    private Integer birdtypeId;
    @Column(name = "price")
    private Integer price;
    @Column(name = "time")
    private Integer time;
}
