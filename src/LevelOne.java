import bagel.*;

public class LevelOne extends LevelBase{
    public LevelOne(){
        super();
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
