import bagel.Input;

import java.util.ArrayList;
import java.util.List;

public class LevelThree extends LevelBase{
    private final Guardian guardian = new Guardian();
    private static final int CLEAR_SCORE_THREE = 350;
    private List<Enemy> enemies = new ArrayList<>();
    /**
     * default constructor
     */
    public LevelThree(){
        super();
        this.clearScore = CLEAR_SCORE_THREE;
        this.levelNumber = 3;
    }

    public void update(Input input) {
        currFrame++;
        changeSpeed();
        for (Lane lane: lanes) {
            // checks whether Double Score Note is pressed
            if(isDoubled() && doubledFrame <= 480){
                score += (lane.update(input, accuracy) * 2);

            }else if(doubledFrame > 480){
                deactivateDouble();
            }
            else {
                score += lane.update(input, accuracy);
            }
        }
        guardian.update(input, enemies);
        enemyHitsNotes();
        accuracy.update();
        finished = checkFinished();
    }
    public void enemyHitsNotes(){
        for (Lane lane: lanes) {
            for (Enemy enemy: enemies){
                if(enemy.isActivated()) {
                    lane.enemyCollision(enemy.getCurrentLocation());
                }
            }
        }
    }

}
