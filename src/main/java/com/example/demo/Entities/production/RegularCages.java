package com.example.demo.Entities.production;

import com.example.demo.Entities.dbo.Cart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "regular_cages", schema = "production")
public class RegularCages implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cage_id")
    private Integer cageId;
    @Column(name = "birdtype_id")
    private Integer birdtypeId;
    @Column(name = "cage_code")
    private String cageCode;
    @Column(name = "cage_name")
    private String cageName;
    @Column(name = "cage_price")
    private Integer cagePrice;
    @Column(name = "short_decription")
    private String shortDecription;
    @Column(name = "long_decription")
    private String longDecription;
    @Column(name = "cage_color")
    private String cageColor;
    @Column(name = "cage_material")
    private String cageMaterial;
    @Column(name = "cage_shape")
    private String cageShape;
    @Column(name = "cage_size")
    private String cageSize;
    @Column(name = "cage_spokes")
    private Integer cageSpokes;
    @Column(name = "cage_feet")
    private String cageFeet;
    @Column(name = "cage_perch")
    private String cagePerch;
    @Column(name = "cage_cups")
    private String cageCups;
    @Column(name = "cage_hanger")
    private String cageHanger;
    @Column(name = "cage_rate")
    private Integer cageRate;
    @Column(name = "enabled")
    private Integer enabled;
    @Column(name = "discount")
    private Float discount;
    @Column(name = "quantity")
    private Integer quantity;
    @OneToMany(mappedBy = "regularCages")
    @ToString.Exclude
    @JsonIgnore
    private List<Cart> carts;
}