/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author borbier
 */
public class GameImage {
    private final Image image;
    private ImageView imageView;
    
    // Constructors.
    public GameImage(String imageLocation) throws FileNotFoundException {
        this.image = new Image(new FileInputStream(imageLocation));
        this.imageView = new ImageView(image);
        
        imageView.setX(0);
        imageView.setY(0);
        
        imageView.setPreserveRatio(true);
    }
    
    public GameImage(String imageLocation, int x, int y) throws FileNotFoundException {
        this.image = new Image(new FileInputStream(imageLocation));
        this.imageView = new ImageView(image);
        
        imageView.setX(x);
        imageView.setY(y);
    }
    
    public GameImage(String imageLocation, int x, int y, int width, int height) throws FileNotFoundException {
        this.image = new Image(new FileInputStream(imageLocation));
        this.imageView = new ImageView(image);
        
        imageView.setX(x);
        imageView.setY(y);
        
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }
    
    public void show(Pane screen) {
        screen.getChildren().add(imageView);
    }
    
    public void show(Group group) {
        group.getChildren().add(imageView);
    }
}
