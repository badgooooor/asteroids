/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author borbier
 */
public class Player extends GameObject {
    public Player() {
        super(new Rectangle(40, 20, Color.BLUE));
    } 
}
