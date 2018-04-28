/**** implemented for 60/2 OOP ****/
/*
 * This interface served the classes that is a text HUD with values. (Ex. player's health and score)
 */
package asteroids.interfaceClass;

import javafx.scene.layout.Pane;

/**
 *
 * @author borbier
 */
public interface TextValueInterface {
    public void updateHUD(int valueChange);
    public void showHUD();
    public void add(Pane screen);
    public void reset();
    
    public double getValue();
}
