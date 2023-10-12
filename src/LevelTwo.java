import bagel.Input;

public class LevelTwo extends LevelBase{
    private static final int CLEAR_SCORE_TWO = 400;
    public LevelTwo(){
        super();
        this.clearScore = CLEAR_SCORE_TWO;
        this.levelNumber = 2;
    }
    public void update(Input input) {
        currFrame++;
        changeSpeed();
        for (Lane lane: lanes) {
            if(isDoubled() && doubledFrame <= 480){
                score += (lane.update(input, accuracy) * 2);

            }else if(doubledFrame > 480){
                deactivateDouble();
            }
            else {
                score += lane.update(input, accuracy);
            }
        }

        accuracy.update();
        bombExplodes();
        finished = checkFinished();
    }

}
