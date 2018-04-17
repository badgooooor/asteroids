/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.original;

/**
 *
 * @author borbier
 */
public class Score implements ValueInterface{
    private double score;

    public Score() {
        this.score = 0;
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
}
