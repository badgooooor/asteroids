/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.hud;

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
