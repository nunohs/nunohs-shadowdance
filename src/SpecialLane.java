/*import bagel.*;

import java.util.ArrayList;
import java.util.List;

public class SpecialLane extends Lane{
    private final Image image = new Image("res/laneSpecial.PNG");
    private Keys relevantKey;
    private List<SpecialNote> specialNotes = new ArrayList<SpecialNote>();
    private int currSpecialNote = 0;
    private int numSpecialNotes = 0;
    private static final int ACTIVATED = 50;
    private static final String DOUBLED = "Double Score";

    public SpecialLane(String dir, int location) {
        super(dir, location);
        this.relevantKey = Keys.SPACE;

    }
    @Override
    public int update(Input input, Accuracy accuracy){
        draw();
        for (int k = currSpecialNote; k < numSpecialNotes; k++) {
            specialNotes.get(k).update();
        }
        if(specialNotes.get(currSpecialNote).isActive()  && input.wasPressed(relevantKey)){
            accuracy.evualuteSpecialEffects(specialNotes.get(currSpecialNote).getY(), TARGET_HEIGHT);
        }
        if (currSpecialNote < numSpecialNotes) {
            int score = specialNotes.get(currSpecialNote).checkScore(input, accuracy, TARGET_HEIGHT, relevantKey);
            if (specialNotes.get(currSpecialNote).isCompleted()) {
                currSpecialNote++;
            }

        }


    return 0;
    }
    public void addSpecialNote(SpecialNote sn){
        specialNotes.add(numSpecialNotes++, sn);}
}
 */