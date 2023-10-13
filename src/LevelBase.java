import bagel.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
/**
 * The abstract class for all levels
 */
public abstract class LevelBase {
    protected static int currFrame;
    protected static boolean doubled = false;
    protected List<Lane> lanes;
    protected boolean started;
    protected boolean finished;
    protected Accuracy accuracy;
    protected int levelNumber;
    protected int numLanes;
    protected int clearScore;

    protected int score;
    protected static int doubledFrame = 0;
    protected static boolean speedUp = false;
    protected static boolean speedDown = false;
    protected static int speedChange = 0;

    /**
     * Default constructor for each level
     */
    public LevelBase() {
        this.currFrame = 0;
        this.lanes = new ArrayList<>();
        this.started = true;
        this.finished = false;
        this.accuracy = new Accuracy();
        this.score = 0;
        this.numLanes = 0;
    }

    /**
     * sets the speedUp flag to be true
     */

    public static void speedUpActive(){
        speedUp = true;
        speedChange = 1;
    }
    /**
     * Flag indicating whether speedUp is active
     * @return flag for speedUp
     */
    public static boolean isSpeedUpActive(){
        return speedUp;
    }
    /**
     * Flag indicating whether speedDown is active
     * @return flag for speedDown
     */
    public static boolean isSpeedDownActive() {
        return speedDown;
    }
    /**
     * sets the speedDown flag to be true
     */
    public static void speedDownActive(){
        speedDown = true;
        speedChange = -1;
    }
    /**
     * sets the doubled flag to be true
     */
    public static void doubleActive(){
        doubled = true;
        doubledFrame = 0;
    }
    /**
     * sets the doubled flag to be false and resets the doubledFrame
     */
    public static void deactivateDouble(){
        doubled = false;
        doubledFrame = 0;
    }

    /**
     * Flag indicating whether doubled is active
     * @return flag for doubled
     */
    public static boolean isDoubled() {
        return doubled;
    }

    /**
     * resets both flag for speedUp and speedDown
     */
    public void deActivateSpeedChange(){
        speedUp = false;
        speedDown = false;
    }

    /**
     * gets the current frame of the levels
     * @return current frame of the levels
     */
    public static int getCurrFrame(){
        return currFrame;
    }
    /**
     * Checks if the level is done
     * @return true if level is finished, false if not.
     */
    protected boolean checkFinished() {
        for (Lane lane: lanes) {
            if (!lane.isFinished()) {
                return false;
            }
        }
        deactivateDouble();
        deActivateSpeedChange();
        return true;
    }
    /**
     * Reads CSV file and adds the respective lanes and notes
     * @param levelNumber of a level
     */
    private void readCsv(int levelNumber) {
        try (BufferedReader br = new BufferedReader(new FileReader("res/level" + levelNumber + ".csv"))) {
            String textRead;
            while ((textRead = br.readLine()) != null) {
                String[] splitText = textRead.split(",");

                if (splitText[0].equals("Lane")) {
                    // reading lanes
                    String laneType = splitText[1];
                    int pos = Integer.parseInt(splitText[2]);
                    Lane lane = new Lane(laneType, pos);
                    lanes.add(numLanes++, lane);

                } else {
                    // reading notes
                    String dir = splitText[0];
                    Lane lane = null;
                    for (int i = 0; i < numLanes; i++) {
                        if (lanes.get(i).getType().equals(dir)) {
                            lane = lanes.get(i);
                        }
                    }

                    if (lane != null) {
                        switch (splitText[1]) {
                            case "Normal":
                                Note note = new Note("res/note" + dir + ".png", Integer.parseInt(splitText[2]));
                                lane.addNote(note);
                                break;
                            case "Hold":
                                HoldNote holdNote = new HoldNote("res/holdNote" + dir + ".PNG",
                                        Integer.parseInt(splitText[2]));
                                lane.addHoldNote(holdNote);
                                break;
                            case "SpeedUp":
                                SpecialNote speedUpNote = new SpecialNote("res/noteSpeedUp.png",
                                        Integer.parseInt(splitText[2]), (splitText[1]));
                                lane.addSpecialNote(speedUpNote);
                                break;
                            case "SlowDown":
                                SpecialNote slowDownNote = new SpecialNote("res/noteSlowDown.png",
                                        Integer.parseInt(splitText[2]),(splitText[1]) );
                                lane.addSpecialNote(slowDownNote);
                                break;
                            case "DoubleScore":
                                SpecialNote doubleScoreNote = new SpecialNote("res/note2x.png",
                                        Integer.parseInt(splitText[2]),"2x");
                                lane.addSpecialNote(doubleScoreNote);
                                break;
                            case "Bomb":
                                SpecialNote bombNote = new SpecialNote("res/note" + splitText[1] + ".png",
                                        Integer.parseInt(splitText[2]),splitText[1]);
                                lane.addNote(bombNote);
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
    /**
     * runs the specific level initiated
     */
    public void runLevel() {
        readCsv(levelNumber);
        started = true;
    }
    /**
     * Updates the game for each frame
     * @param input of the user
     */
    public abstract void update(Input input);

    /**
     * Calculates whether user cleared the level
     */
    public boolean endLevel(){
        if(score >= clearScore){
            return true;
        }
        return false;
    }
    /**
     * @return score of the level
     */
    public int getScore() {
        return score;
    }
    /**
     * @ Changes the speed for all notes when speedUp or speedDown is pressed
     */
    public void changeSpeed(){
        if(isSpeedUpActive()){
            for (Lane lane: lanes) {
                lane.changeSpeed(speedChange);
            }
            speedUp = false;
        } else if (isSpeedDownActive()){
            for (Lane lane: lanes) {
                lane.changeSpeed(speedChange);
            }
            speedDown = false;
        }
    }
}
