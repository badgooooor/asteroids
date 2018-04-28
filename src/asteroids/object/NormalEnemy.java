/**** implemented for 60/2 OOP ****/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author borbier
 */
    // A sitting still enemy.
public class NormalEnemy extends Enemy {
    public NormalEnemy() {
        super(new Circle(15,15,15, Color.RED));
    }
}
