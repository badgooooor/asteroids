/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        tname.setTranslateY(y+24);
    }
    
    public void showHUD() {
        tscore.setText("HI : " + (int)(score.getValue()));
        tname.setText("PLAYER : " + (score.getName()));
    }
    
    @Override
    public void updateHUD(int valueChange) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void show(Pane screen) {
       screen.getChildren().add(vb);
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getValue() {
        return score.getValue();
    }
    
    public void updateHUD(Score score) {
        this.score = score;
        this.showHUD();
    }
}
