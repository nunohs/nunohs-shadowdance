import bagel.*;
import bagel.util.Point;

/**
 * Class for the Enemy which steals normal notes
 */
public class Enemy {
    private int SPEED = -1;
    private final Image enemyImage = new Image("res/enemy.PNG");
    private double xCoord;
    private double yCoord;

    private static final int MAX_WIDTH = 900;
    private static final int MIN_WIDTH = 100;
    private static final int MIN_HEIGHT = 100;
    private static final int MAX_HEIGHT = 500;
    private static final int INCLUSIVITY = 99;
    private boolean activated = true;

    /**
     * default contructor
     */
    public Enemy(){
        this.randomLocation();

    }
    /**
     * @return x coordinate of enemy
     */
    public double getxCoord() {
        return xCoord;
    }

    /**
     * @return y coordinate of enemy
     */
    public double getyCoord() {
        return yCoord;
    }

    /**
     * @return Flag indicating if enemy is activated
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * @return Current Point of enemy
     */
    public Point getCurrentLocation(){
        return new Point(getxCoord(),getyCoord());
    }

    /**
     * Sets the enemy into a random initial location
     */
    public void randomLocation(){
        xCoord = Math.random()*(MAX_WIDTH - INCLUSIVITY) + MIN_WIDTH;
        yCoord = Math.random()*(MAX_HEIGHT - INCLUSIVITY) + MIN_HEIGHT;

    }
    /**
     * Updates the enemy
     */
    public void update() {
        if (activated) {
            if (xCoord < 0 || xCoord > Window.getWidth()) {
                SPEED *= -1;
            }
            xCoord += SPEED;
            enemyImage.draw(xCoord, yCoord);
        }
    }
    /**
     * deactivates the enemy
     */
    public void deactivate(){
        activated = false;
    }

}
