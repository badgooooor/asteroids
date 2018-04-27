/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Server
 */
public class NukeEnemy extends Enemy{
    
    public NukeEnemy() {
        super(new Circle(15,15,15, Color.WHITE));
    }
    
}
