/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.original;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author borbier
 */
class TrackingEnemy extends Enemy {
    private double playerx;
    private double playery;
    
    private double dx;
    private double dy;
    
    // Constructor.
    TrackingEnemy() {
        super(new Circle(10, 10, 10, Color.RED));
    }
    
    public void track(GameObject player) {
        System.out.println("Tracking player");

        // Get player co-ordinate.
        double playerX = player.getView().getTranslateX();
        double playerY = player.getView().getTranslateY();

        // dy and dx.
        double dx = this.getView().getTranslateX() - playerX;
        double dy = this.getView().getTranslateY() - playerY;
        
        // Debug.
        System.out.println("Tracking player : "+dx+","+dy);
        // Making vector and move it move it
        
        
    }
}
