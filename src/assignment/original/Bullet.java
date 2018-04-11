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
class Bullet extends GameObject {
    Bullet() {
        super(new Circle(5, 5, 5, Color.BROWN));
    }
}
