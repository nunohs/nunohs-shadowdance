import bagel.*;
public class Enemy {
    private static int SPEED = -1;
    private final Image enemyImage = new Image("res/enemy.PNG");
    private double xCoord;
    private final double initialX;
    private double yCoord;
    private final double initialY;
    private static final int MAX_WIDTH = 900;
    private static final int MIN_WIDTH = 100;
    private static final int MIN_HEIGHT = 100;
    private static final int MAX_HEIGHT = 500;
    private static final int INCLUSIVITY = 99;
    private boolean activated = true;

    public Enemy(){
        randomLocation();
        this.initialX = xCoord;
        this.initialY = yCoord;


    }
    public void randomLocation(){
        xCoord = Math.random()*(MAX_WIDTH - INCLUSIVITY) + MIN_WIDTH;
        yCoord = Math.random()*(MAX_HEIGHT - INCLUSIVITY) + MIN_HEIGHT;

    }
    public void update() {
        if (activated) {
            if (xCoord < 0 || xCoord > Window.getWidth()) {
                SPEED *= -1;
            }
            xCoord += SPEED;
            enemyImage.draw(xCoord, yCoord);
        }
    }
    public void deactivate(){

    }

}
