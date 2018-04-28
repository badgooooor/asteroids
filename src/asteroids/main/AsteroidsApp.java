package asteroids.main;

import asteroids.audio.GameAudio;
import asteroids.hud.HealthHUD;
import asteroids.hud.ScoreHUD;
import asteroids.hud.GameOverText;
import asteroids.hud.HighScoreHUD;
import asteroids.hud.TextBox;
import asteroids.object.*;
import asteroids.score.HighscoreManager;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Original source code from,
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 * 
 * @author Yuttakhan Baingen
 * @author Jirawat KaewKulrabut
 * @author Pichamol Phothong Computer Engineering, KMITL.
 */
public class AsteroidsApp extends Application {
    // Initialize objects and variables.
    // Config variables.
    final private int NUMBER_OF_ENEMIES = 40;     // Number of enemies on-screen.
    final private int MAX_PLAYER_HEALTH = 3;      // Max player's health in-game.

    private Pane root;

    // Game elements.
    private List<GameObject> bullets = new ArrayList<>();
    private List<GameObject> enemies = new ArrayList<>();
    private ScoreHUD playerScore;
    private HighScoreHUD highScore;
    private HealthHUD playerHealth;
    private GameOverText gameOver;
    private GameObject player;
    private GameImage world;
    
    // Menu elements.
    private Scene menu, game;
    private int page = 0;

    AnimationTimer timer;
    
    HighscoreManager hm = new HighscoreManager();
    
    // Music and sound effects.
    GameAudio gameSound = new GameAudio();            
    String musicFile = "src/res/gameMusic.mp3";     
    Media sound = new Media(new File(musicFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    
    /**** implemented for 60/2 OOP ****/
    // Initialize game's object.
    private Parent StateGameStart() {
        // Set up pane & size.
        root = new Pane();
        root.setPrefSize(600, 600);
        
        // Add objects, HUD.
        try {
            world = new GameImage("src\\res\\bg.png");
            world.add(root);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AsteroidsApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        player = new Player();      
        player.setVelocity(new Point2D(1, 0));
        addGameObject(player, 300, 300);

        playerScore = new ScoreHUD(470, 30);
        playerScore.add(root);

        playerHealth = new HealthHUD(MAX_PLAYER_HEALTH);
        playerHealth.add(root);
        
        gameOver = new GameOverText(playerScore);
        gameOver.show(root);
        
        highScore = new HighScoreHUD(hm.getTopScore(), 250, 30);
        highScore.add(root);
        
        // Set to update state.
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                StateGameUpdate();
            }
        };
        timer.start();

        return root;
    }
    
    
    // Spawn methods. (bullets, enemies)
    private void addBullet(GameObject bullet, double x, double y) {         
        bullets.add(bullet);
        addGameObject(bullet, x, y);
    }
    private void addEnemy(GameObject enemy, double x, double y) {
        enemies.add(enemy);
        addGameObject(enemy, x, y);
    }
    private void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }
    /**** implemented for 60/2 OOP ****/
    // Randomizer for spawn enemies. (special enemies will also having a special condition in order to trigger spawning.
    private void EnemySpawn() {
        double enemy_rand = Math.random();
        if (enemy_rand < 0.01 && playerHealth.getValue() < 3) {                                                             // HEALER -- player needs to lose health.
            addEnemy(new HealingEnemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        } else if(enemy_rand > 0.01 && enemy_rand < 0.02 && enemies.size() > (NUMBER_OF_ENEMIES * 0.6)) {                   // NUKE -- many enemies.(60% +)
            addEnemy(new NukeEnemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        } else if (enemy_rand > 0.02 && enemy_rand < 0.08) {
            addEnemy(new NormalEnemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        } else if (enemy_rand > 0.08 && enemy_rand < 0.10) {
            addEnemy(new TrackingEnemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        }
    }
    
    /**** implemented for 60/2 OOP ****/
    // Update State 
    private void StateGameUpdate() {
        // Check collision between bullets & enemies.
        for (GameObject bullet : bullets) {
            for (GameObject enemy : enemies) {
                if (bullet.isColliding(enemy)) {
                    bullet.setAlive(false);
                    enemy.setAlive(false);
                    // Scoring -- rewards varies to how tough(high reward) or player-helping enemies(low reward + helps) or normal.
                    if (enemy instanceof TrackingEnemy) {
                        playerScore.updateHUD(15);              // update & play sound.
                        gameSound.playBangSmallSound();       
                    } else if (enemy instanceof NormalEnemy) {
                        playerScore.updateHUD(10);              
                        gameSound.playBangLargeSound();         
                    } else if (enemy instanceof HealingEnemy) {
                        playerScore.updateHUD(5);
                        playerHealth.updateHUD(1);
                    } else if (enemy instanceof NukeEnemy) {
                        playerScore.updateHUD(5);
                        for (GameObject e: enemies) {                                   // Kill them all!
                            root.getChildren().remove(e.getView());
                            e.setAlive(false);
                        }
                    }
                    root.getChildren().removeAll(bullet.getView(), enemy.getView());    // remove bullets and enemies.
                }
            }
        }
        // Check collision between enemies & player.
        for (GameObject enemy : enemies) {
            if (player.isColliding(enemy)) {
                enemy.setAlive(false);
                playerHealth.updateHUD(-1);
                root.getChildren().remove(enemy.getView());
            }
        }
        // Death condition. -- trigger game over.
        if (playerHealth.getValue() == 0) {
            StatePlayerDeath();
        }
        // Remove bullets & enemies.
        bullets.removeIf(GameObject::isDead);
        enemies.removeIf(GameObject::isDead);
        // Update each instances.
        bullets.forEach(GameObject::update);
        enemies.forEach(GameObject::update);
        player.update();
        // Tracking enemies chasing player.
        for (GameObject e : enemies) {
            if (e instanceof TrackingEnemy) {
                e.movement(player);
            } 
        }
        // Random enemy spawning.
        if (enemies.size() < NUMBER_OF_ENEMIES) {
            EnemySpawn();
        }
    }
    
    /**** implemented for 60/2 OOP ****/
    // Player death state -- trigger when player's dead.
    public void StatePlayerDeath() {
        gameSound.playBangLargeSound(); 
        player.setAlive(false);
        root.getChildren().remove(player.getView());
        gameOver.display();
        timer.stop();
        hm.addScore(playerScore.getScore().getName(), (int) playerScore.getValue());    // Get score and set score.
        highScore.updateHUD(hm.getTopScore());                                          
        System.out.println(hm.getHighscoreString());    // Debug.
    }
    
    /**** implemented for 60/2 OOP ****/
    // Reset the game. -- Press R.
    // Clear enemies and return to main menu.
    public void StateReset() {
        for (GameObject enemy : enemies) {
            root.getChildren().remove(enemy.getView());
        }
        enemies.clear();    

        player.setAlive(true);
        player.getView().setTranslateX(root.getPrefWidth() / 2);
        player.getView().setTranslateY(root.getPrefHeight() / 2);

        gameOver.remove();

        playerHealth.reset();
        playerScore.reset();
        root.getChildren().add(player.getView());
    }

    // Scene setting.
    @Override
    public void start(Stage stage) throws Exception {
        root = new Pane();
        root.setPrefSize(600, 600);
        
        // Add button.
        Button change = new Button("PLAY GAME");
        Group groupMenu = new Group();
        
        change.setStyle("-fx-font:28  arial; -fx-base: #FFFFFF;");
        groupMenu.getChildren().addAll(root, change);
        change.setLayoutX(270);
        change.setLayoutY(250);
        
        // Add images and object in menu.
        try {
            GameImage imageMenu = new GameImage("src\\res\\grid-war-main-title.png", 10, 70);
            imageMenu.add(groupMenu);
            GameImage imageHowto = new GameImage("src\\res\\grid-wars-tutorial.png", 240, 275, 300, 350);
            imageHowto.add(groupMenu);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AsteroidsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Player's name text box.
        TextBox playerName = new TextBox(295,210);
        playerName.add(groupMenu);

        menu = new Scene(groupMenu);            // Group menu elements and display.

        stage.setScene(menu);
        change.setOnAction(a -> {
            game = new Scene(StateGameStart());
            stage.setScene(game);
            playerScore.getScore().setName(playerName.getName());
            // Player Input. & bullet shooting.
            stage.getScene().setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.LEFT) {
                    player.rotateLeft();
                } else if (e.getCode() == KeyCode.RIGHT) {
                    player.rotateRight();
                } else if (e.getCode() == KeyCode.SPACE && player.isAlive()) {
                    gameSound.playShotingSound();                 //add
                    Bullet bullet = new Bullet();
                    bullet.setVelocity(player.getVelocity().normalize().multiply(5));
                    addBullet(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());
                    // Clear screen and restart the game.    
                } else if (e.getCode() == KeyCode.R && player.isDead()) {
                    StateReset();
                    stage.setScene(menu);
                } 
            });

        });
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);       //add
        mediaPlayer.play();
        
        stage.show();
    }

    // Launching application.
    public static void main(String[] args) {
        launch(args);
    }
}
