/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.original;

import javafx.scene.layout.Pane;

/**
 *
 * @author borbier
 */
public interface TextHUDInterface {
    public void updateValue(int valueChange);
    public void showValue();
    public void show(Pane screen);
    public void reset();
}
