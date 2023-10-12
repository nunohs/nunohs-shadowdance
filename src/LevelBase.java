import bagel.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class LevelBase {
    protected static int currFrame;
    protected List<Lane> lanes;
    protected boolean started;
    protected boolean finished;
    protected Accuracy accuracy;
    protected int levelNumber;
    protected int numLanes;
    protected int clearScore;
    private final Font SCORE_FONT = new Font(FONT_FILE, 30);
    public final static String FONT_FILE = "res/FSO8BITR.TTF";
    protected int score;
    private final static int SCORE_LOCATION = 35;

    public LevelBase() {
        this.currFrame = 0;
        this.lanes = new ArrayList<>();
        this.started = true;
        this.finished = false;
        this.accuracy = new Accuracy();
        this.score = 0;
        this.numLanes = 0;
    }

    public static int getCurrFrame(){
        return currFrame;
    }
    protected boolean checkFinished() {
        for (Lane lane: lanes) {
            if (!lane.isFinished()) {
                return false;
            }
        }
        return true;
    }
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
                                Note note = new Note(dir, Integer.parseInt(splitText[2]));
                                lane.addNote(note);
                                break;
                            case "Hold":
                                HoldNote holdNote = new HoldNote(dir, Integer.parseInt(splitText[2]));
                                lane.addHoldNote(holdNote);
                                break;
                            case "SpeedUp":
                                SpecialNote speedUpNote = new SpecialNote("SpeedUp", Integer.parseInt(splitText[2]));
                                lane.addSpecialNote(speedUpNote);
                                break;
                            case "SlowDown":
                                SpecialNote slowDownNote = new SpecialNote("SlowDown", Integer.parseInt(splitText[2]));
                                lane.addSpecialNote(slowDownNote);
                                break;
                            case "DoubleScore":
                                SpecialNote doubleScoreNote = new SpecialNote("2x", Integer.parseInt(splitText[2]));
                                lane.addSpecialNote(doubleScoreNote);
                                break;
                            case "Bomb":
                                Note bombNote = new Note("Bomb", Integer.parseInt(splitText[2]));
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
    public void runLevel() {
        readCsv(levelNumber);
        started = true;
    }
    public abstract void update(Input input);

    public boolean endLevel(){
        if(score <= clearScore){
            return false;
        }
        return true;
    }

    public int getScore() {
        return score;
    }
}
