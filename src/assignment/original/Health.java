/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.original;

/**
 *
 * @author borbier
 */
public class Health {
    private int health;
    private final int maxHealth = 3;
    public Health() {
    }

    public Health(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health <= maxHealth && health >= 0) {
        this.health = health;
        }
        System.out.println("set health"+health);
    }
    
    
}
