/*
 * Added nuke enemy -- clear all enemies when shot.
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
