package com.dxValley.AgroFinance.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private double score;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FarmerData> farmerData;

    public Score(double score) {

        this.score = score;

    }

}
