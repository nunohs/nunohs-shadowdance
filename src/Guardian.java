import bagel.*;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;
/**
 * Class for Guardian that handles enemies and projectiles
 */
public class Guardian {
    private final Image guardianImage = new Image("res/guardian.PNG");
    private static final Point GUARDIAN_POINT = new Point(800,600);

    private int numEnemy = 0;
    private final int currEnemy = 0;
    private final static int ENEMY_FRAME_SPAWN = 600;
    private List<Projectile> projectileList = new ArrayList<Projectile>();
    private final static int MAX_DISTANCE = 10000;
    private final static int COLLISION = 62;

    /**
     * Updates guardian, enemy, and projectile
     */
    public void update(Input input, List<Enemy> enemies ){
        guardianImage.draw(GUARDIAN_POINT.x,GUARDIAN_POINT.y);

        if((LevelBase.getCurrFrame() % ENEMY_FRAME_SPAWN) == 0){
            createEnemy(enemies);

        }
        for (int i= currEnemy ; i < numEnemy; i++) {
            enemies.get(i).update();
        }
        // Creates a new projectile
        if(input.wasPressed(Keys.LEFT_SHIFT) && numEnemy != 0 && checkActiveEnemy( enemies) ){
            Projectile projectile = new Projectile(closestEnemyToGuardian( enemies).getCurrentLocation());
            projectileList.add(projectile);
        }
        for(Projectile projectiles: projectileList){
            projectiles.update();

            for (int i= 0 ; i < numEnemy; i++) {
                if(enemies.get(i).isActivated()){
                    if(projectiles.getProjectileCurrentPoint().distanceTo(enemies.get(i).getCurrentLocation())
                            <= COLLISION && projectiles.isActive()){
                        projectiles.deActivate();
                        enemies.get(i).deactivate();
                        break;
                    }
                }
            }

        }
    }
    /**
     * Creates a new Enemy object
     */
    public void createEnemy(List<Enemy> enemies){
        Enemy newEnemy = new Enemy();
        enemies.add(numEnemy, newEnemy);
        numEnemy++;
    }

    /**
     * Finds the closest Enemy from the Guardian
     */
    public Enemy closestEnemyToGuardian(List<Enemy> enemies){
        double closestEnemyDistance = MAX_DISTANCE;
        int closestEnemyIndex = -1;
        for (int i= currEnemy ; i < numEnemy; i++) {
            if (enemies.get(i).isActivated()) {
                if (enemies.get(i).getCurrentLocation().distanceTo(GUARDIAN_POINT) < closestEnemyDistance) {
                    closestEnemyDistance = enemies.get(i).getCurrentLocation().distanceTo(GUARDIAN_POINT);
                    closestEnemyIndex = i;
                }
            }
        }
        return enemies.get(closestEnemyIndex);
    }

    /**
     * Checks whether there are enemies active on the game
     */
    public boolean checkActiveEnemy(List<Enemy> enemies){
        for (int i= currEnemy ; i < numEnemy; i++) {
            if (enemies.get(i).isActivated()) {
                return true;
            }
        }
        return false;
    }
}

