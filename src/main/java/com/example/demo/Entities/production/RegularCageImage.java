package com.example.demo.Entities.production;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "regular_cage_image", schema = "production")
public class RegularCageImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "cage_id")
    private Integer cageId;
    @Column(name = "is_main")
    private Integer isMain;
    @Column(name = "image_list_cage_id")
    private Integer imageListCageId;
}