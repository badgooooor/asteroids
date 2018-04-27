/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.hud;

import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author Server
 */
public class TextBox {
    public TextField textBox;
    HBox hb;

    public TextBox() {
        this.textBox = new TextField();
        textBox.setScaleX(2);
        textBox.setScaleY(1.5);
        textBox.setText("Your Name");
        hb = new HBox();
        hb.getChildren().addAll(textBox);
        hb.setLayoutX(270);
        hb.setLayoutY(170);
        hb.setSpacing(8);
    }

    public TextField getTextBox() {
        return textBox;
    }

    public String getName() {
        return textBox.getText();
    }



    public void show(Group screen) {
       screen.getChildren().add(hb);
    }
}
