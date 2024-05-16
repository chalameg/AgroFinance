package com.dxValley.AgroFinance.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DecisionRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private  double startValue;
    private  double endValue;
    private  double amountDecided;
    private  String description;
    private  String standard;

    public DecisionRule(double startValue, double endValue, double amountDecided,String description, String standard){

        this.startValue=startValue;
        this.endValue=endValue;
        this.amountDecided=amountDecided;
        this.description=description;
        this.standard=standard;


    }

}
