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
public class Score {
    private double score;

    public Score() {
        this.score = 0;
    }
    
    // Add / Subtract score.
    public void addScore(double addedScore) {
        this.score = this.score + addedScore;
    }
    
    public void subtractScore(double subtractScore) {
        this.score = this.score - subtractScore;
    }
    
    public double getScore() {
        return score;
    }
}
