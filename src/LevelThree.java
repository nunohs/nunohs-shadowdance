import bagel.Input;

public class LevelThree extends LevelBase{
    private Guardian guardian = new Guardian();
    public LevelThree(){
        super();
        this.levelNumber = 3;
    }

    public void update(Input input) {
        currFrame++;
        for (Lane lane: lanes) {
            score += lane.update(input, accuracy);
        }
        guardian.update(input);
        accuracy.update();
        finished = checkFinished();
    }

}
