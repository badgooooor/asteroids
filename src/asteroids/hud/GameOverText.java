/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.hud;

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
public class GameOverText implements TextHUDInterface{
    // Initialize variables.
    private Text titleText;
    private Text finalScoreText;
    private VBox vb;
    private ScoreHUD score;
    public GameOverText(ScoreHUD score) {
        titleText = new Text();
        finalScoreText = new Text();
        vb = new VBox();
        this.score = score;
        
        vb.getChildren().addAll(titleText, finalScoreText);
        
        // Set-up.
        titleText.setFill(Color.RED);
        titleText.setFont(Font.font(null, FontWeight.BOLD, 32));
        titleText.setX(0);
        titleText.setY(0);
        titleText.setTranslateX(300);
        titleText.setTranslateY(300);
        
        finalScoreText.setFill(Color.RED);
        finalScoreText.setFont(Font.font(null, FontWeight.BOLD, 28));
        finalScoreText.setX(0);
        finalScoreText.setY(0);
        finalScoreText.setTranslateX(340);
        finalScoreText.setTranslateY(340);
    }
    
    @Override
    public void show(Pane screen) {
        screen.getChildren().add(vb);
    }
    
    @Override
    public void display() {
        titleText.setText("GAME OVER");
        finalScoreText.setText("Your score : " + score.getValue());
    }
    
    @Override
    public void remove() {
        titleText.setText("");
        finalScoreText.setText("");
    }
}
