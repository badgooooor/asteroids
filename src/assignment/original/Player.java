/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.original;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author borbier
 */
class Player extends GameObject {
    Health playerHealth;
    Score playerScore;
    
    Player() {
        super(new Rectangle(40, 20, Color.BLUE));
        playerScore = new Score();
    }

    public Health getPlayerHealth() {
        return playerHealth;
    }
    
    public Score getPlayerScore() {
        return playerScore;
    }
    
}
