import bagel.*;

public class LevelOne extends LevelBase{
    private static final int CLEAR_SCORE_ONE = 150;
    public LevelOne(){
        super();
        this.clearScore = CLEAR_SCORE_ONE;
        this.levelNumber = 1;

    }

    public void update(Input input) {
        currFrame++;
        for (Lane lane: lanes) {
            score += lane.update(input, accuracy);

        }
        accuracy.update();
        finished = checkFinished();
    }


}
