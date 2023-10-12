import bagel.*;

/**
 * Class for normal notes
 */
public class Note {
    protected final Image image;
    protected int appearanceFrame;
    protected int speed = 2;
    protected int y = 100;
    protected boolean active = false;
    protected boolean completed = false;
    protected int xCoord = 0;

    public Note(String dir, int appearanceFrame) {
        image = new Image(dir);
        this.appearanceFrame = appearanceFrame;
    }
    public void setSpeed(int tempSpeed){
        speed += tempSpeed;
    }

    public boolean isActive() {
        return active;
    }
    public boolean isCompleted() {return completed;}

    public void deactivate() {
        active = false;
        completed = true;
    }
    public int getY() {
        return y;
    }

    public void update() {
        if (active) {
            y += speed;
        }

        if (LevelBase.getCurrFrame() >= appearanceFrame && !completed) {
            active = true;
        }
    }

    public void draw(int x) {
        if (active) {
            this.xCoord = x;
            image.draw(x, y);
        }
    }

    public int getxCoord() {
        return xCoord;
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
