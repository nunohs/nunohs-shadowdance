import bagel.Input;

public class LevelThree extends LevelBase{
    private Guardian guardian = new Guardian();
    private static final int CLEAR_SCORE_THREE = 350;
    public LevelThree(){
        super();
        this.clearScore = CLEAR_SCORE_THREE;
        this.levelNumber = 3;

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
        guardian.update(input);
        accuracy.update();
        finished = checkFinished();
    }

}
