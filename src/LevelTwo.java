import bagel.Input;

public class LevelTwo extends LevelBase{
    public LevelTwo(){
        super();
        this.levelNumber = 2;
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
