/*
 * The classes is used for getting player's name to fill in the high score.
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
    
    // Constructor -- set the textbox up.
    public TextBox(int x, int y) {
        this.textBox = new TextField();
        textBox.setScaleX(2);
        textBox.setScaleY(1.5);
        textBox.setText("Your Name");
        hb = new HBox();
        hb.getChildren().addAll(textBox);
        hb.setLayoutX(x);
        hb.setLayoutY(y);
        hb.setSpacing(8);
    }
    
    public TextField getTextBox() {
        return textBox;
    }

    public String getName() {
        return textBox.getText();
    }

    public void add(Group screen) {
       screen.getChildren().add(hb);
    }
}
