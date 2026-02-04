package com.workintech.s18d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "burger", schema= "fsweb_18_4")
@Validated
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    private String name;
    @Min(0)
    private Double price;
    private boolean vegan;

    @Column(name = "bread_type")
    @Enumerated(EnumType.STRING)
    @Size(max = 255)
    private BreadType breadType;

    @Size(max = 255)
    private String contents;

}
