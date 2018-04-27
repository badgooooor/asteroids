/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.score;

import asteroids.interfaceClass.ValueInterface;
import java.io.Serializable;

/**
 *
 * @author borbier
 */
public class Score implements ValueInterface, Serializable{
    private double score;
    private String name;
    
    // Constructor
    public Score() {
        this.score = 0;
    }
    
    public Score(String name, double score) {
        this.name = name;
        this.score = score;
    }
    
    // Getter/ Setter for name.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
      
    // Add / Subtract score.
    @Override
    public void addValue(double added) {
        this.score = this.score + added;
    }

    @Override
    public void subtractValue(double subtracted) {
        this.score = this.score - subtracted;
    }

    @Override
    public double getValue() {
        return score;
    }
    
    @Override
    public void reset() {
        this.score = 0;
    }
}
