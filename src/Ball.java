import java.awt.*;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Ball extends GOval {
    private static final int BALL_SIZE = 20;
    private static final int VELOCITY_X_MIN = 3;
    private static final int VELOCITY_X_MAX = 4;
    private int velocityY = 4;
    private int velocityX = RandomGenerator.getInstance().nextInt(VELOCITY_X_MIN, VELOCITY_X_MAX);


    public Ball(double position_x, double position_y) {
        super(position_x, position_y, BALL_SIZE, BALL_SIZE);
        this.setFilled(true);
        this.setFillColor(Color.DARK_GRAY);
    }

    public void ballMove() {
        this.move(velocityX, velocityY);
    }

    public void switchYVelocity() {
        this.velocityY *= -1;
    }

    public void switchXVelocity() {
        this.velocityX *= -1;
    }
}

