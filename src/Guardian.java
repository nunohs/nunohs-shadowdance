import bagel.*;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;

public class Guardian {
    private final Image guardianImage = new Image("res/guardian.PNG");
    private static final Point GUARDIAN_POINT = new Point(800,600);
    private Enemy[] enemies = new Enemy[20];
    private int numEnemy = 0;
    private int currEnemy = 0;
    private final static int ENEMY_FRAME_SPAWN = 600;
    List<Projectile> projectileList = new ArrayList<Projectile>();
    public void update(Input input){
        guardianImage.draw(GUARDIAN_POINT.x,GUARDIAN_POINT.y);

        if(input.wasPressed(Keys.LEFT_SHIFT)){
            Projectile projectile = new Projectile();
        }

    }
}
