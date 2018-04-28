/**** implemented for 60/2 OOP ****/
/*
 * Head-up display for display current player score. -- implemented from TextValueInterface
 */
package asteroids.hud;

import asteroids.score.Score;
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
public class ScoreHUD implements TextValueInterface{
    // Initialize variables.
    private Score score;
    private Text tscore;
    private VBox vb;
    
    // Constructor.
    public ScoreHUD(int x, int y) {
        tscore = new Text();
        vb = new VBox();
        score = new Score();
        
        vb.getChildren().add(tscore);
        
        // Set up
        tscore.setText("SCORE : " + (int)(score.getValue()));
        tscore.setFill(Color.WHITE);
        tscore.setFont(Font.font(null, FontWeight.BOLD, 24));
        
        tscore.setX(100);
        tscore.setY(100);
        
        tscore.setTranslateX(x);
        tscore.setTranslateY(y);
        
    }
    
    // Override from HUD interface class.
    @Override
    public void updateHUD(int valueChange) {
        if(valueChange >= 0) {
            score.addValue(valueChange);
        } else {
            score.subtractValue(Math.abs(valueChange));
        }
        showHUD();
    }
    
    // display the player's score.
    @Override
    public void showHUD() {
        tscore.setText("Score : " + (int)(score.getValue()));
    }
    
    @Override
    public void add(Pane screen) {
        screen.getChildren().add(vb);
    }
    
    @Override
    public void reset() {
        score.reset();
        this.showHUD();
    }
    
    @Override
    public double getValue() {
        return score.getValue();
    }
    
    public Score getScore() {
        return score;
    }
}
