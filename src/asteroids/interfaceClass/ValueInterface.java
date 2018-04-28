/*
 * This interface served the classes that is a value.(Ex. score, health)
 */
package asteroids.interfaceClass;

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
