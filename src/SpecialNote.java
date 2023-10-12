import bagel.*;

public class SpecialNote extends Note {
    //private int y = 100;
    //private final Image image;
    // private final int appearanceFrame;
    private final String type;

    public SpecialNote(String dir, int appearanceFrame, String type) {
        super(dir, appearanceFrame);
        this.type = type;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    @Override
    public void update() {
        if (active) {
            y += speed;
        }

        if (LevelBase.getCurrFrame() >= appearanceFrame && !completed) {
            active = true;
        }
        if (y >= (Window.getHeight())){
            deactivate();
        }
    }

    /*public int checkScore(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if (isActive()) {
            // evaluate accuracy of the key press
            int score = accuracy.evaluateScore(y, targetHeight, input.wasPressed(relevantKey));

            if (score != Accuracy.NOT_SCORED) {
                deactivate();
                return score;
            }

        }

        return 0;
    }

     */
}
