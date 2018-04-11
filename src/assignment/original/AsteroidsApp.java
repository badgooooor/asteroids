package assignment.original;


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

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class AsteroidsApp extends Application {
    // Config variables.
    final private int numberOfEnemies = 50;
    
    private Pane root;
    
    // Array of enemies & bullets.
    private List<GameObject> bullets = new ArrayList<>();
    private List<GameObject> enemies = new ArrayList<>();
    
    // Player object.
    private GameObject player;
    private GameObject tracker;
    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(600, 600);

        player = new Player();
        player.setVelocity(new Point2D(1, 0));
        addGameObject(player, 300, 300);
        
        // Testing enemy.
        tracker = new TrackingEnemy();
        addGameObject(tracker, 400, 500);
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

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

    private void onUpdate() {
        for (GameObject bullet : bullets) {
            for (GameObject enemy : enemies) {
                if (bullet.isColliding(enemy)) {
                    bullet.setAlive(false);
                    enemy.setAlive(false);

                    root.getChildren().removeAll(bullet.getView(), enemy.getView());
                }
            }
        }

        bullets.removeIf(GameObject::isDead);
        enemies.removeIf(GameObject::isDead);

        bullets.forEach(GameObject::update);
        enemies.forEach(GameObject::update);

        player.update();
        
        // Debug.
        tracker.track(player);
        // Random enemy spawning.
        // + Add ememy limits on the view. 
        // To-do : - Edit spawn border.
//        if (enemies.size() < numberOfEnemies) {
//            EnemySpawn();
//        }
        
    }
    
    // Enemy spawning
    private void EnemySpawn() {
        double enemy_rand = Math.random();
        if (enemy_rand < 0.01) {
            addEnemy(new TrackingEnemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        }else if(enemy_rand > 0.01 && enemy_rand < 0.02) {
            addEnemy(new NormalEnemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        }
    }
    // Enemy border-spawning debug.
    /*
    calculate : Math.rand.range(0.03 * root.getPrefWidth()) and Math.rand.range(0.97 * root.getPrefHeight())
    */

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        // Player Input. & bullet shooting.
        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                player.rotateLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                player.rotateRight();
            } else if (e.getCode() == KeyCode.SPACE) {
                Bullet bullet = new Bullet();
                bullet.setVelocity(player.getVelocity().normalize().multiply(5));
                addBullet(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
