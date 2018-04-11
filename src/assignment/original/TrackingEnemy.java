/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.original;


import java.util.Random;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
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
 * Bug need to fixed : - Random chasing position.
 */
class TrackingEnemy extends Enemy {
    private double randomDistance;    
    private Random rand;
    // Constructor.
    TrackingEnemy() {
        super(new Circle(10, 10, 10, Color.RED));
        this.rand = new Random();
    }
    
    // Tracking method.
    @Override
    public void track(GameObject player) {
        // Create path.
        Path path = new Path();
        
        // Get self co-ordinate.
        double x = this.getView().getTranslateX();
        double y = this.getView().getTranslateY();
        // Get player co-ordinate.
        double playerX = player.getView().getTranslateX();
        double playerY = player.getView().getTranslateY();

        // .
        double dx = this.getView().getTranslateX() - playerX;
        double dy = this.getView().getTranslateY() - playerY;
        
        // Creating
        MoveTo moveTo = new MoveTo();
        LineTo lineTo = new LineTo();
        
        moveTo.setX(playerX);
        moveTo.setY(playerY);
        
        lineTo.setX(dx + random());
        lineTo.setY(dy + random());
        
        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        
        // Set transistion
        PathTransition transition = new PathTransition();
        transition.setDuration(Duration.seconds(30.0));
        transition.setDelay(Duration.seconds(3.0));
        transition.setPath(path);
        transition.setNode(this.getView());
        transition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(false);
        
        transition.play();
    }
    
    public double random() {
        return rand.nextInt(30);
    }
}
