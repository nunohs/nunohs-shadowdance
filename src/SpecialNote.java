import bagel.Image;
import bagel.Input;
import bagel.Keys;

public class SpecialNote {
    private final int speed = 2;
    private int y = 100;
    private final Image image;
    private final int appearanceFrame;
    private boolean active = false;
    private boolean completed = false;

    public SpecialNote(String dir, int appearanceFrame) {
        image = new Image("res/note" + dir + ".png");
        this.appearanceFrame = appearanceFrame;
    }


    public boolean isActive() {
        return active;
    }
    public boolean isCompleted() {return completed;}
    public void deactivate() {
        active = false;
        completed = true;
    }
    public void draw(int x) {
        if (active) {
            image.draw(x, y);
        }
    }
    public void update() {
        if (active) {
            y += speed;
        }

        if (ShadowDance.getCurrFrame() >= appearanceFrame && !completed) {
            active = true;
        }
    }
    public int checkScore(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
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
}
