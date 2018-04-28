/*
 * This interface served the classes that is normal head-up display.
 */
package asteroids.interfaceClass;

import javafx.scene.layout.Pane;

/**
 *
 * @author borbier
 */
public interface TextHUDInterface {
    public void show(Pane screen);
    public void display();
    public void remove();
}
