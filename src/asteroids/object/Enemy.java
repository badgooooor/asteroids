/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import javafx.scene.Node;

/**
 *
 * @author borbier
 */
abstract class Enemy extends GameObject {
    public Enemy(Node view) {
        super(view);
    }
}