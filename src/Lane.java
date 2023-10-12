import bagel.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the lanes which notes fall down
 */
public class Lane {
    private static final int HEIGHT = 384;
    protected static final int TARGET_HEIGHT = 657;
    private final String type;
    private final Image image;
    //private final Note[] notes = new Note[100];
    private List<Note> notes = new ArrayList<Note>();
    private int numNotes = 0;
    // CHANGE THIS TO ARRAYLIST
    //private final HoldNote[] holdNotes = new HoldNote[30];
    private List<HoldNote> holdNotes = new ArrayList<HoldNote>();
    //private final SpecialNote[] specialNotes = new SpecialNote[40];
    private List<SpecialNote> specialNotes = new ArrayList<SpecialNote>();
    private int numHoldNotes = 0;
    private int numSpecialNotes = 0;
    private Keys relevantKey;
    private final int location;
    private int currNote = 0;
    private int currHoldNote = 0;
    private int currSpecialNote = 0;

    public Lane(String dir, int location) {
        this.type = dir;
        this.location = location;
        image = new Image("res/lane" + dir + ".png");
        switch (dir) {
            case "Left":
                relevantKey = Keys.LEFT;
                break;
            case "Right":
                relevantKey = Keys.RIGHT;
                break;
            case "Up":
                relevantKey = Keys.UP;
                break;
            case "Down":
                relevantKey = Keys.DOWN;
                break;
            case "Special":
                relevantKey = Keys.SPACE;
                break;
        }
    }

    public String getType() {
        return type;
    }

    /**
     * updates all the notes in the lane
     */
    public int update(Input input, Accuracy accuracy) {
        draw();

        for (int i = currNote; i < numNotes; i++) {
            notes.get(i).update();
        }

        for (int j = currHoldNote; j < numHoldNotes; j++) {
            holdNotes.get(j).update();
        }
        for (int k = currSpecialNote; k < numSpecialNotes; k++) {
            specialNotes.get(k).update();
        }


        if (currNote < numNotes) {
            int score = notes.get(currNote).checkScore(input, accuracy, TARGET_HEIGHT, relevantKey);
            if (notes.get(currNote).isCompleted()) {
                currNote++;
                return score;
            }
        }

        if (currHoldNote < numHoldNotes) {
            int score = holdNotes.get(currHoldNote).checkScore(input, accuracy, TARGET_HEIGHT, relevantKey);
            if (holdNotes.get(currHoldNote).isCompleted()) {
                currHoldNote++;
            }
            return score;
        }
        if (currSpecialNote < numSpecialNotes) {
            int score = specialNotes.get(currSpecialNote).checkScore(input, accuracy, TARGET_HEIGHT, relevantKey);
            if (specialNotes.get(currSpecialNote).isCompleted()) {
                currSpecialNote++;
            }
            return score;
        }



        return Accuracy.NOT_SCORED;
    }

    public void addNote(Note n) {
        notes.add(numNotes++, n);
    }

    public void addHoldNote(HoldNote hn) {holdNotes.add(numHoldNotes++, hn);
    }
    public void addSpecialNote(SpecialNote sn){
        specialNotes.add(numSpecialNotes++, sn);}

    /**
     * Finished when all the notes have been pressed or missed
     */
    public boolean isFinished() {
        for (int i = 0; i < numNotes; i++) {
            if (!notes.get(i).isCompleted()) {
                return false;
            }
        }

        for (int j = 0; j < numHoldNotes; j++) {
            if (!holdNotes.get(j).isCompleted()) {
                return false;
            }
        }
        for (int i = 0; i < numSpecialNotes; i++) {
            if (!specialNotes.get(i).isCompleted()) {
                return false;
            }
        }

        return true;
    }

    /**
     * draws the lane and the notes
     */
    public void draw() {
        image.draw(location, HEIGHT);

        for (int i = currNote; i < numNotes; i++) {
            notes.get(i).draw(location);
        }
        for (int j = currHoldNote; j < numHoldNotes; j++) {
            holdNotes.get(j).draw(location);
        }
        for (int k = currSpecialNote; k < numSpecialNotes; k++) {
           specialNotes.get(k).draw(location);
        }
    }



}
