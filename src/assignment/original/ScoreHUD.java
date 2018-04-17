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
public class ScoreHUD {
    Score score;
    Text tscore;
    VBox vb;

    public ScoreHUD() {
        tscore = new Text();
        vb = new VBox();
        score = new Score();
        
        vb.getChildren().add(tscore);
        
        // Set up
        tscore.setText("Score : " + (int)(score.getValue()));
        tscore.setFill(Color.BLACK);
        tscore.setFont(Font.font(null, FontWeight.BOLD, 24));
        
        tscore.setX(100);
        tscore.setY(100);
        
        tscore.setTranslateX(470);
        tscore.setTranslateY(30);
        
    }
    
    public void updateScore(int scoreChange) {
        if(scoreChange >= 0) {
            score.addValue(scoreChange);
        } else {
            score.subtractValue(Math.abs(scoreChange));
        }
        tscore.setText("Score : " + (int)(score.getValue()));
    }
    
    public void show(Pane screen) {
        screen.getChildren().add(vb);
    }
}
