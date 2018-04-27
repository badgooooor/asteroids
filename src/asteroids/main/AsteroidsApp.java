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
    // Config variables.
    final private int NUMBER_OF_ENEMIES = 50;     // Number of enemies on-screen.
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
    //Sounds.
    GameAudio gameSound = new GameAudio();            
    String musicFile = "src/res/gameSounds.mp3";     
    Media sound = new Media(new File(musicFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    
    private Parent StateGameStart() {
        // Set up pane & size.
        root = new Pane();
        root.setPrefSize(600, 600);
 
        try {
            world = new GameImage("src\\res\\bg.png");
            world.show(root);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AsteroidsApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        player = new Player();
        player.setVelocity(new Point2D(1, 0));
        addGameObject(player, 300, 300);

        playerScore = new ScoreHUD(470, 30);
        playerScore.show(root);

        playerHealth = new HealthHUD(MAX_PLAYER_HEALTH);
        playerHealth.show(root);
        
        gameOver = new GameOverText(playerScore);
        gameOver.show(root);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                StateGameUpdate();
            }
        };
        timer.start();

        return root;
    }

    // Enemy spawning methods.
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
    private void EnemySpawn() {
        double enemy_rand = Math.random();
        if (enemy_rand < 0.01) {
            addEnemy(new HealingEnemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        } else if (enemy_rand > 0.01 && enemy_rand < 0.02) {
            addEnemy(new NormalEnemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        } else if (enemy_rand > 0.02 && enemy_rand < 0.03) {
            addEnemy(new TrackingEnemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        }
    }

    private void StateGameUpdate() {
        // Check collision between bullets & enemies.
        for (GameObject bullet : bullets) {
            for (GameObject enemy : enemies) {
                if (bullet.isColliding(enemy)) {
                    bullet.setAlive(false);
                    enemy.setAlive(false);
                    // Scoring.
                    if (enemy instanceof TrackingEnemy) {
                        playerScore.updateHUD(15);              // update & play sound.
                        gameSound.playBangSmallSound();       
                    } else if (enemy instanceof NormalEnemy) {
                        playerScore.updateHUD(10);              
                        gameSound.playBangLargeSound();         
                    } else if (enemy instanceof HealingEnemy) {
                        playerScore.updateHUD(10);
                        playerHealth.updateHUD(1);
                    }
                    root.getChildren().removeAll(bullet.getView(), enemy.getView());    // remove.
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
        // Death condition.
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

    public void StatePlayerDeath() {
        gameSound.playBangLargeSound();
        player.setAlive(false);
        root.getChildren().remove(player.getView());
        gameOver.display();
        timer.stop();
        hm.addScore(playerScore.getScore().getName(), (int) playerScore.getValue());
        
        System.out.println(hm.getHighscoreString());
    }

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
        Button change = new Button("PLAY GAME");
        Group groupMenu = new Group();

        change.setStyle("-fx-font:28  arial; -fx-base: #FFFFFF;");
        groupMenu.getChildren().addAll(root, change);
        change.setLayoutX(300);
        change.setLayoutY(200);
        
        // Add images and object in menu.
        try {
            GameImage imageMenu = new GameImage("src\\res\\grid-war-main-title.png", 0, 20);
            imageMenu.show(groupMenu);
            GameImage imageHowto = new GameImage("src\\res\\grid-wars-tutorial.png", 400, 275, 300, 350);
            imageHowto.show(groupMenu);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AsteroidsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        TextBox playerName = new TextBox();
        playerName.show(groupMenu);

        menu = new Scene(groupMenu);

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
