import bagel.*;
import bagel.util.Point;
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
    public Projectile(Point enemyLocation){
        setProjectileDirection(enemyLocation);
        this.angleProjectile = Math.atan2(enemyLocation.y - projectileInitialPoint.y,
                enemyLocation.x - projectileInitialPoint.x);
        this.rotation.setRotation(angleProjectile);
        this.active = true;
    }
    public Point getProjectileCurrentPoint(){
        return new Point(xCoord, yCoord);
    }

    public void update(){
        outOfBounds();
        if(active) {
            xCoord += SPEED * projectileDirectionX;
            yCoord += SPEED * projectileDirectionY;
            projectileImage.draw(xCoord, yCoord,rotation);
        }
    }

    public void deActivate(){
        active = false;
    }

    public void setProjectileDirection(Point enemyLocation) {
        double distance = projectileInitialPoint.distanceTo(enemyLocation);
        projectileDirectionX = (enemyLocation.x-projectileInitialPoint.x)/distance;
        projectileDirectionY = (enemyLocation.y-projectileInitialPoint.y)/distance;
    }

    public boolean isActive() {
        return active;
    }
    public void outOfBounds(){
        if (xCoord < 0 || xCoord > Window.getWidth()){
            active = false;
        }
        if (yCoord < 0 || yCoord > Window.getWidth()){
            active = false;
        }
    }
}
