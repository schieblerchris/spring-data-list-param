package com.github.schieblerchris.playground.spring.datalistparam;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "t_cities")
@Data
@NoArgsConstructor
public class CityEt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String region;

    private String city;
}
