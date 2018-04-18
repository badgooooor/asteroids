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
public interface ValueInterface {
    public void addValue(double added);
    public void subtractValue(double subtracted);
    public double getValue();
    public void reset();
}
