import bagel.*;

/**
 * Class for special note object
 */
public class SpecialNote extends Note {
    private final String type;

    /**
     * Constructs a new Special note object
     */
    public SpecialNote(String dir, int appearanceFrame, String type) {
        super(dir, appearanceFrame);
        this.type = type;
    }

    /**
     * @return type of special note
     */
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
}
