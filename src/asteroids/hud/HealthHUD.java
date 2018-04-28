/*
 * Head-up display class, display Player's health -- implemented from TextValueInterface
 */
package asteroids.hud;

import asteroids.health.Health;
import asteroids.interfaceClass.TextValueInterface;
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
public class HealthHUD implements TextValueInterface {
    private Health health;
    private Text text;
    private VBox vb;
    
    // Constructor -- set the text up.
    public HealthHUD(int maxhealth) {
        // Initialize objects.
        text = new Text();
        vb = new VBox();
        health = new Health(maxhealth);
        
        vb.getChildren().add(text);
        
        // Set up
        text.setText("Health : " + (int)(health.getValue()));
        text.setFill(Color.WHITE);
        text.setFont(Font.font(null, FontWeight.BOLD, 23));
        
        text.setX(100);
        text.setY(105);
        
        text.setTranslateX(30);
        text.setTranslateY(31);
    }
    
    // Update score.
    @Override
    public void updateHUD(int valueChange) {
        if(valueChange >= 0) {
            health.addValue(valueChange);
        } else {
            health.subtractValue(Math.abs(valueChange));
        }
        showHUD();
    }
    
    // show head-up display.
    @Override
    public void showHUD() {
        text.setText("Health : " + (int)(health.getValue()));
    }
    
    // add to screen.
    @Override
    public void add(Pane screen) {
        screen.getChildren().add(vb);
    }
    
    // reset the text to initial status.
    @Override
    public void reset() {
        health.reset();
        this.showHUD();
    }
    
    @Override
    public double getValue() {
        return health.getValue();
    }
}
