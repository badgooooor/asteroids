/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.hud;

/**
 *
 * @author borbier
 */
public class Health implements ValueInterface{
    private int health;
    private final int maxHealth;
    public Health(int maxHealth) {
        this.health = maxHealth;
        this.maxHealth = maxHealth;
    }

    @Override
    public void addValue(double added) {
        if(this.health <= maxHealth) {
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
