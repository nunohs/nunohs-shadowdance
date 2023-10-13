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

    /**
     * Constructor for new Note
     * @param dir is the directory for the image,
     *            appearanceFrame is the frame when note is supposed to be active
     */
    public Note(String dir, int appearanceFrame) {
        image = new Image(dir);
        this.appearanceFrame = appearanceFrame;
    }
    /**
     * Changes the speed of the note
     */
    public void setSpeed(int tempSpeed){
        speed += tempSpeed;
    }

    /**
     * Flag indicating if note is active
     */
    public boolean isActive() {
        return active;
    }
    /**
     * Flag indicating note has been completed
     */
    public boolean isCompleted() {return completed;}

    /**
     * Deactivates note
     */
    public void deactivate() {
        active = false;
        completed = true;
    }
    /**
     * @return y coordiante of note
     */
    public int getY() {
        return y;
    }

    /**
     * Updates note
     */
    public void update() {
        if (active) {
            y += speed;
        }

        if (LevelBase.getCurrFrame() >= appearanceFrame && !completed) {
            active = true;
        }
    }

    /**
     * Draws Note
     */
    public void draw(int x) {
        if (active) {
            this.xCoord = x;
            image.draw(x, y);
        }
    }

    /**
     * @return x coordinate of note
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * Checks the score of note and deactivates if pressed
     * @param input of user, accuracy of note, targerHeight of the targetNote, relevantKey of the note
     * @return score of note
     */
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
