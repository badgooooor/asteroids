/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.original;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author borbier
 */
public class HealthHUD implements TextHUDInterface {
    Health health;
    Text text;
    VBox vb;

    public HealthHUD(int maxhealth) {
        // Initialize objects.
        text = new Text();
        vb = new VBox();
        health = new Health(maxhealth);
        
        vb.getChildren().add(text);
        
        // Set up
        text.setText("Health : " + (int)(health.getValue()));
        text.setFill(Color.BLACK);
        text.setFont(Font.font(null, FontWeight.BOLD, 24));
        
        text.setX(100);
        text.setY(100);
        
        text.setTranslateX(30);
        text.setTranslateY(30);
    }
    
    @Override
    public void updateValue(int valueChange) {
        if(valueChange >= 0) {
            health.addValue(valueChange);
        } else {
            health.subtractValue(Math.abs(valueChange));
        }
        showValue();
    }
    
    @Override
    public void showValue() {
        text.setText("Health : " + (int)(health.getValue()));
    }
    
    @Override
    public void show(Pane screen) {
        screen.getChildren().add(vb);
    }
    
    @Override
    public void reset() {
        health.reset();
        this.showValue();
    }
}
