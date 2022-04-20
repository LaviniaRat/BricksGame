import java.awt.*;

import acm.graphics.*;

public class Paddle extends GRect {

    private static final int PADDLE_WIDTH = 50;
    private static final int PADDLE_HEIGHT = 8;
    private final int canvasHeight;


    public Paddle(double position_x, double position_y, int canvasHeight) {
        super(position_x, position_y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.canvasHeight = canvasHeight;
        this.setFilled(true);
        this.setFillColor(Color.DARK_GRAY);
    }
}
