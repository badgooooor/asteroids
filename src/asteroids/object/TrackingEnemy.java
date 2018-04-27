/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;


import java.util.Random;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author borbier
 * Tracker == The chasing invaders.
 */
public class TrackingEnemy extends Enemy {   
    private double speed = 0.25; 
    // Constructor.
    public TrackingEnemy() {
        super(new Circle(10, 10, 10, Color.ORANGE));
    }
    
    // Tracking method.
   @Override
    public void movement(GameObject player) {
        // Get distance between player and enemy.
        double playerX = player.getView().getTranslateX();
        double playerY = player.getView().getTranslateY();
        double enemyX = this.getView().getTranslateX();
        double enemyY = this.getView().getTranslateY();
        
        // Set tracking vector.
        double distance = Math.sqrt(Math.pow(playerX - enemyX, 2) + Math.pow(playerY - enemyY, 2));
        double x = (playerX - enemyX) / distance;
        double y = (playerY - enemyY) / distance;
        
        this.setVelocity(new Point2D(x * speed, y * speed));
    }
}
