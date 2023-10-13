import bagel.*;

/**
 * Class for hold notes
 */
public class HoldNote extends Note{

    private static final int HEIGHT_OFFSET = 82;
    private boolean holdStarted = false;
    private final static int Y_HOLD = 24;

    public HoldNote(String dir, int appearanceFrame) {
        super(dir,appearanceFrame);
        this.y = Y_HOLD;
    }

    @Override
    public void update() {
        super.update();
    }

    public void startHold() {
        holdStarted = true;
    }

    /**
     * scored twice, once at the start of the hold and once at the end
     */
    public int checkScore(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if (isActive() && !holdStarted) {
            int score = accuracy.evaluateScore(getBottomHeight(), targetHeight, input.wasPressed(relevantKey));

            if (score == Accuracy.MISS_SCORE) {
                deactivate();
                return score;
            } else if (score != Accuracy.NOT_SCORED) {
                startHold();
                return score;
            }
        } else if (isActive() && holdStarted) {

            int score = accuracy.evaluateScore(getTopHeight(), targetHeight, input.wasReleased(relevantKey));

            if (score != Accuracy.NOT_SCORED) {
                deactivate();
                return score;
            } else if (input.wasReleased(relevantKey)) {
                deactivate();
                accuracy.setAccuracy(Accuracy.MISS);
                return Accuracy.MISS_SCORE;
            }
        }

        return 0;
    }

    /**
     * gets the location of the start of the note
     */
    private int getBottomHeight() {
        return y + HEIGHT_OFFSET;
    }

    /**
     * gets the location of the end of the note
     */
    private int getTopHeight() {
        return y - HEIGHT_OFFSET;
    }
}
