package com.dxValley.AgroFinance.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    private  double score;

    //relate to user by userAccount or from other end point get from farmer information

//    @OneToMany(mappedBy = "farmerAccount", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<FarmerData> farmerData = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<FarmerData> farmerData;

    public Score(double score){

        this.score=score;

    }

}
