/*
 * Added enemies -- heal the player on shot.
 */
package asteroids.object;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Server
 */
public class HealingEnemy extends Enemy {
    public HealingEnemy() {
        super(new Circle(15,15,15, Color.GREEN));
    }
}
