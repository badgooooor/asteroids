/**** implemented for 60/2 OOP ****/
/*
 * Added class for serving images in game.
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
    
    // Constructors. -- get just normal image path.
    public GameImage(String imageLocation) throws FileNotFoundException {
        this.image = new Image(new FileInputStream(imageLocation));
        this.imageView = new ImageView(image);
        
        imageView.setX(0);
        imageView.setY(0);
        
        imageView.setPreserveRatio(true);
    }
    
    // Constructors -- get image path and xy co-ordinate.
    public GameImage(String imageLocation, int x, int y) throws FileNotFoundException {
        this.image = new Image(new FileInputStream(imageLocation));
        this.imageView = new ImageView(image);
        
        imageView.setX(x);
        imageView.setY(y);
    }
    
    // Constructor -- get image path, xy co-ordinate and its size.
    public GameImage(String imageLocation, int x, int y, int width, int height) throws FileNotFoundException {
        this.image = new Image(new FileInputStream(imageLocation));
        this.imageView = new ImageView(image);
        
        imageView.setX(x);
        imageView.setY(y);
        
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }
    
    // Add image to group or pane.
    public void add(Pane screen) {
        screen.getChildren().add(imageView);
    }
    
    public void add(Group group) {
        group.getChildren().add(imageView);
    }
}
