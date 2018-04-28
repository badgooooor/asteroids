/*
 * This class is for import the sound effects and playing it.
 */
package asteroids.audio;

import java.io.File;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * @author Babe
 */
public class GameAudio {
    // import sound effects and initialize as audioclip.
    private final String shootingPath = "src/res/fire.wav";    
    private final String bangSmallPath = "src/res/bangSmall.wav";
    private final String bangLargePath = "src/res/bangLarge.wav";
    
    private AudioClip shootingAudio;
    private AudioClip bangSmallAudio;
    private AudioClip bangLargeAudio;
    
    public GameAudio() {
        shootingAudio = new AudioClip(new File(shootingPath).toURI().toString());
        bangSmallAudio = new AudioClip(new File(bangSmallPath).toURI().toString());
        bangLargeAudio = new AudioClip(new File(bangLargePath).toURI().toString());
    }
    
    // play each sound.
    public void playShotingSound(){
        shootingAudio.play();
    }
    
    public void playBangSmallSound(){
        bangSmallAudio.play();
    }
    
    public void playBangLargeSound(){
        bangLargeAudio.play();
    }
    
}
