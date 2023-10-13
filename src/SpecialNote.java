import bagel.*;

public class SpecialNote extends Note {
    private final String type;

    public SpecialNote(String dir, int appearanceFrame, String type) {
        super(dir, appearanceFrame);
        this.type = type;
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
}
