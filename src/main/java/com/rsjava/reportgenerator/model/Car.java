package com.rsjava.reportgenerator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String color;
    private Boolean isUsed;
    private LocalDate buildDate;

    public Car(String brand, String model, String color, Boolean isUsed, LocalDate buildDate) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.isUsed = isUsed;
        this.buildDate = buildDate;
    }
}
