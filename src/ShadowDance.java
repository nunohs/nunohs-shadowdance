import bagel.*;

/**
 * Uses Sample solution for SWEN20003 Project 1, Semester 2, 2023 for
 * the base (Level 1) By Stella Li
 * @ Hadi Nuno Handrison
 */
public class ShadowDance extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW DANCE";
    private final Image BACKGROUND_IMAGE = new Image("res/background.png");

    public final static String FONT_FILE = "res/FSO8BITR.TTF";
    private final static int TITLE_X = 220;
    private final static int TITLE_Y = 250;
    private final static int INS_X_OFFSET = 100;
    private final static int INS_Y_OFFSET = 190;
    private final static int SCORE_LOCATION = 35;
    private final Font TITLE_FONT = new Font(FONT_FILE, 64);
    private final Font INSTRUCTION_FONT = new Font(FONT_FILE, 24);
    private final Font SCORE_FONT = new Font(FONT_FILE, 30);
    private static final String INSTRUCTIONS = "SELECT LEVELS WITH\nNUMBER KEYS\n\n 1          2            3";

    private static final String CLEAR_MESSAGE = "CLEAR!";
    private static final String TRY_AGAIN_MESSAGE = "TRY AGAIN";
    private static final String END_SCREEN_MESSAGE = "PRESS SPACE TO RETURN TO LEVEL SELECTION";
    private static final int END_Y_ONE = 300;
    private static final int END_Y_TWO = 500;
    private boolean started = false;
    private LevelBase levelPlaying;

    public ShadowDance(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);

    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowDance game = new ShadowDance();
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }

        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        if (!started) {
            // starting screen
            TITLE_FONT.drawString(GAME_TITLE, TITLE_X, TITLE_Y);
            INSTRUCTION_FONT.drawString(INSTRUCTIONS,
                    TITLE_X + INS_X_OFFSET, TITLE_Y + INS_Y_OFFSET);

            if (input.wasPressed(Keys.NUM_1)) {
                started = true;
                levelPlaying = new LevelOne();
                levelPlaying.runLevel();

            }
            else if (input.wasPressed(Keys.NUM_2)) {
                started = true;
                levelPlaying = new LevelTwo();
                levelPlaying.runLevel();

            }
            else if (input.wasPressed(Keys.NUM_3)) {
                started = true;
                levelPlaying = new LevelThree();
                levelPlaying.runLevel();

            }

        } else if (levelPlaying.checkFinished()) {
            // end screen
            if (levelPlaying.endLevel()) {
                TITLE_FONT.drawString(CLEAR_MESSAGE,
                        WINDOW_WIDTH/2 - TITLE_FONT.getWidth(CLEAR_MESSAGE)/2,
                        END_Y_ONE);
            } else {
                TITLE_FONT.drawString(TRY_AGAIN_MESSAGE,
                        WINDOW_WIDTH/2 - TITLE_FONT.getWidth(TRY_AGAIN_MESSAGE)/2,
                        END_Y_ONE);
            }
            INSTRUCTION_FONT.drawString(END_SCREEN_MESSAGE,
                    WINDOW_WIDTH/2 - INSTRUCTION_FONT.getWidth(END_SCREEN_MESSAGE)/2,
                    END_Y_TWO);
            if(input.wasPressed(Keys.SPACE)){
                started=false;
            }
        }
        else {
            // gameplay
            SCORE_FONT.drawString("Score " + levelPlaying.getScore(), SCORE_LOCATION, SCORE_LOCATION);
            levelPlaying.update(input);

        }

    }
}
