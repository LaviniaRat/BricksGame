import acm.graphics.GCompound;
import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.*;

public class Bricks extends GCompound {
    private static final int BRICK_WIDTH = 30;
    private static final int BRICK_HEIGHT = 15;
    private static final int ROW_NUMBER = 10;
    private static final int COLUMN_NUMBER = 10;

    public Bricks(int x, int y) {
        double position_X = 0;
        double position_Y = 90;
        int space = 1;
        double initial_posX = position_X;
        for (int i = 0; i < ROW_NUMBER; i++) {
            Color color = RandomGenerator.getInstance().nextColor();
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                GRect brick = new GRect(position_X, position_Y, BRICK_WIDTH, BRICK_HEIGHT);
                position_X = position_X + BRICK_WIDTH + space;
                brick.setFilled(true);
                brick.setFillColor(color);
                this.add(brick);
            }
            position_X = initial_posX;
            position_Y += BRICK_HEIGHT + space;
        }
    }

    public void removeBricks(GPoint point) {
        this.remove(this.getElementAt(point));
    }

    public int countBricks() {
        return this.getElementCount();
    }
}
