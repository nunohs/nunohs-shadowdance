import bagel.*;
import bagel.util.Point;
/**
 * Class for projectile object
 */
public class Projectile {
    private final Image projectileImage = new Image("res/arrow.PNG");
    private final static int SPEED = 6;
    private double xCoord = 800;
    private double yCoord = 600;
    private final Point projectileInitialPoint = new Point(800,600);
    private double projectileDirectionX = 0;
    private double projectileDirectionY = 0;
    private boolean active = false;
    private double angleProjectile = 0;
    private final DrawOptions rotation = new DrawOptions();
    /**
     * Constructs a new Projectile
     * @param enemyLocation is te location of enemy when projectile is made
     */
    public Projectile(Point enemyLocation){
        setProjectileDirection(enemyLocation);
        this.angleProjectile = Math.atan2(enemyLocation.y - projectileInitialPoint.y,
                enemyLocation.x - projectileInitialPoint.x);
        this.rotation.setRotation(angleProjectile);
        this.active = true;
    }
    /**
     * Get the current point of the projectile
     */
    public Point getProjectileCurrentPoint(){
        return new Point(xCoord, yCoord);
    }

    /**
     * Updates projectile
     */
    public void update(){
        outOfBounds();
        if(active) {
            xCoord += SPEED * projectileDirectionX;
            yCoord += SPEED * projectileDirectionY;
            projectileImage.draw(xCoord, yCoord,rotation);
        }
    }

    /**
     * Deactivates projectile
     */
    public void deActivate(){
        active = false;
    }

    /**
     * Sets the direction of each projectile
     */
    public void setProjectileDirection(Point enemyLocation) {
        double distance = projectileInitialPoint.distanceTo(enemyLocation);
        projectileDirectionX = (enemyLocation.x-projectileInitialPoint.x)/distance;
        projectileDirectionY = (enemyLocation.y-projectileInitialPoint.y)/distance;
    }

    /**
     * Flag indicating whether projectile is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Checks whether projectile has gone out of bounds
     */
    public void outOfBounds(){
        if (xCoord < 0 || xCoord > Window.getWidth()){
            active = false;
        }
        if (yCoord < 0 || yCoord > Window.getWidth()){
            active = false;
        }
    }
}
