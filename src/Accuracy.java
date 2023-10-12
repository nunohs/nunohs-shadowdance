import bagel.*;

/**
 * Class for dealing with accuracy of pressing the notes
 */
public class Accuracy {
    public static final int PERFECT_SCORE = 10;
    public static final int GOOD_SCORE = 5;
    public static final int BAD_SCORE = -1;
    public static final int MISS_SCORE = -5;
    public static final int NOT_SCORED = 0;
    public static final String PERFECT = "PERFECT";
    public static final String GOOD = "GOOD";
    public static final String BAD = "BAD";
    public static final String MISS = "MISS";
    private static final int PERFECT_RADIUS = 15;
    private static final int GOOD_RADIUS = 50;
    private static final int BAD_RADIUS = 100;
    private static final int MISS_RADIUS = 200;
    private static final Font ACCURACY_FONT = new Font(ShadowDance.FONT_FILE, 40);
    private static final int RENDER_FRAMES = 30;
    private String currAccuracy = null;
    private int frameCount = 0;
    private static final int ACTIVATED = 50;
    private static final String DOUBLED = "Double Score";
    private static final String SPEED_UP = "Speed Up";
    private static final String SLOW_DOWN = "Slow Down";
    private static final String NONE = "No Effect";
    private static final int SPECIAL = 15;

    public void setAccuracy(String accuracy) {
        currAccuracy = accuracy;
        frameCount = 0;
    }

    public int evaluateScore(int height, int targetHeight, boolean triggered) {
        int distance = Math.abs(height - targetHeight);

        if (triggered) {
            if (distance <= PERFECT_RADIUS) {
                setAccuracy(PERFECT);
                return PERFECT_SCORE;
            } else if (distance <= GOOD_RADIUS) {
                setAccuracy(GOOD);
                return GOOD_SCORE;
            } else if (distance <= BAD_RADIUS) {
                setAccuracy(BAD);
                return BAD_SCORE;
            } else if (distance <= MISS_RADIUS) {
                setAccuracy(MISS);
                return MISS_SCORE;
            }

        } else if (height >= (Window.getHeight())) {
            setAccuracy(MISS);
            return MISS_SCORE;
        }

        return NOT_SCORED;

    }
    public String evaluateSpecialEffects(int height, int targetHeight, String noteType){
        int distance = Math.abs(height - targetHeight);
        if(distance <= ACTIVATED ){
            switch (noteType){
                case "SpeedUp":
                    setAccuracy(SPEED_UP);
                    return SPEED_UP;
                case "SlowDown":
                    setAccuracy(SLOW_DOWN);
                    return SLOW_DOWN;
                case "2x":
                    setAccuracy(DOUBLED);
                    return DOUBLED;

            }
        }
        return NONE;
    }

    public void update() {
        frameCount++;
        if (currAccuracy != null && frameCount < RENDER_FRAMES) {
            ACCURACY_FONT.drawString(currAccuracy,
                    Window.getWidth()/2 - ACCURACY_FONT.getWidth(currAccuracy)/2,
                    Window.getHeight()/2);
        }
    }
}
