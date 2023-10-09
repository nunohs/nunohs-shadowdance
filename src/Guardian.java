import bagel.*;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;

public class Guardian {
    private final Image guardianImage = new Image("res/guardian.PNG");
    private static final Point GUARDIAN_POINT = new Point(800,600);
    // CHANGE TO ARRAYLIST
    private Enemy[] enemies = new Enemy[20];
    private int numEnemy = 0;
    private int currEnemy = 0;
    private final static int ENEMY_FRAME_SPAWN = 600;
    List<Projectile> projectileList = new ArrayList<Projectile>();
    private final static int MAX_DISTANCE = 10000;
    private final static int COLLISION = 62;

    public void update(Input input){
        guardianImage.draw(GUARDIAN_POINT.x,GUARDIAN_POINT.y);

        if((ShadowDance.getCurrFrame() % ENEMY_FRAME_SPAWN) == 0){
            createEnemy();

        }
        for (int i= currEnemy ; i < numEnemy; i++) {
            enemies[i].update();
        }

        if(input.wasPressed(Keys.V) && numEnemy != 0 && checkActiveEnemy() ){
            Projectile projectile = new Projectile(closestEnemy().getCurrentLocation());
            projectileList.add(projectile);
        }
        for(Projectile projectiles: projectileList){
            projectiles.update();

            for (int i= 0 ; i < numEnemy; i++) {
                if(enemies[i].isActivated()){
                    if(projectiles.getProjectileCurrentPoint().distanceTo(enemies[i].getCurrentLocation()) <= COLLISION){
                        projectiles.deActivate();
                        enemies[i].deactivate();
                        break;
                    }
                }
            }

        }
    }
    public void createEnemy(){
        Enemy newEnemy = new Enemy();
        enemies[numEnemy]= newEnemy;
        numEnemy++;
    }
    // Finds the closest Enemy from the Guardian
    public Enemy closestEnemy(){
        double closestEnemyDistance = MAX_DISTANCE;
        int closestEnemyIndex = -1;
        for (int i= currEnemy ; i < numEnemy; i++) {
            if (enemies[i].isActivated()) {
                if (enemies[i].getCurrentLocation().distanceTo(GUARDIAN_POINT) < closestEnemyDistance) {
                    closestEnemyDistance = enemies[i].getCurrentLocation().distanceTo(GUARDIAN_POINT);
                    closestEnemyIndex = i;
                }
            }
        }
        return enemies[closestEnemyIndex];
    }
    // Checks whether there are enemies active on the game
    public boolean checkActiveEnemy(){
        for (int i= currEnemy ; i < numEnemy; i++) {
            if (enemies[i].isActivated()) {
                return true;
            }
        }
        return false;
    }
}

