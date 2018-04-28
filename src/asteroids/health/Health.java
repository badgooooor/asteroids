/*
 * Player's health -- implemented from ValueInterface.
 */
package asteroids.health;

import asteroids.interfaceClass.ValueInterface;

/**
 *
 * @author borbier
 */
public class Health implements ValueInterface {
    // Player has current health and maximum health.
    private int health;
    private final int maxHealth;
    
    // Constructor.
    public Health(int maxHealth) {
        this.health = maxHealth;
        this.maxHealth = maxHealth;
    }

    @Override
    public void addValue(double added) {
        if(this.health < maxHealth) {
            this.health = (int)(this.health + added);
        }
    }

    @Override
    public void subtractValue(double subtracted) {
        if (this.health > 0) {
            this.health = (int)(this.health - subtracted);
        }
    }

    @Override
    public double getValue() {
        return this.health;
    }
    
    @Override
    public void reset() {
        this.health = 3;
    }
}
