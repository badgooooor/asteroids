/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public void show(Pane screen);
    public void reset();
    
    public double getValue();
}
