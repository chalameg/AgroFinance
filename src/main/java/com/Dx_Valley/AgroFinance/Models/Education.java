package com.Dx_Valley.AgroFinance.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;
    private Double scoreValue; 
    private Double standard;
    private Double weight;
}
