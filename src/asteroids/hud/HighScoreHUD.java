/*
 * Head-up display for display top player and it's high score -- implemented from TextValueInterface
 */
package asteroids.hud;

import asteroids.interfaceClass.TextValueInterface;
import asteroids.score.Score;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Server
 */
public class HighScoreHUD implements TextValueInterface{
    private Score score;
    private Text tscore;
    private Text tname;
    private VBox vb;
    
    // Constructor -- set the text up.
    public HighScoreHUD(Score score,int x, int y) {
        this.score = score;
        vb = new VBox();
        tscore = new Text();
        tname = new Text();
        
        vb.getChildren().addAll(tscore, tname);
        
        // Set up
        tscore.setText("HI : " + (int)(score.getValue()));
        tscore.setFill(Color.WHITE);
        tscore.setFont(Font.font(null, FontWeight.BOLD, 24));
        
        tscore.setX(100);
        tscore.setY(100);
        
        tscore.setTranslateX(x);
        tscore.setTranslateY(y);
        
        // Set up
        tname.setText("PLAYER : " + (score.getName()));
        tname.setFill(Color.WHITE);
        tname.setFont(Font.font(null, FontWeight.BOLD, 24));
        
        tname.setX(100);
        tname.setY(100);
        
        tname.setTranslateX(x);
        tname.setTranslateY(y+12);
    }
    
    // display the hud.
    public void showHUD() {
        tscore.setText("HIGH : " + (int)(score.getValue()));
        tname.setText("PLAYER : " + (score.getName()));
    }
    
    // Overloaded function from updateHUD.
    public void updateHUD(Score score) {
        this.score = score;
        this.showHUD();
    }
    
    @Override
    public double getValue() {
        return score.getValue();
    }
    
    @Override
    public void add(Pane screen) {
       screen.getChildren().add(vb);
    }
    
    // Unused 
    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void updateHUD(int valueChange) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
