import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import java.awt.event.MouseEvent;
import java.time.Instant;

public class BricksApp extends GraphicsProgram {
    public static final int CANVAS_WIDTH = 300;
    public static final int CANVAS_HEIGHT = 450;
    public static final int MIN_PAUSE = 100;
    public static final int MAX_PAUSE = 110;

    private Bricks bricks;
    private Paddle paddle;
    private Ball ball;
    private GLabel label;

    public void run() {
        //set canvas
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);

        //add bricks
        int x = 100;
        int y = 100;
        this.bricks = new Bricks(x, y);
        add(bricks);

        //add paddle
        this.paddle = new Paddle(125, 442, CANVAS_HEIGHT);
        add(paddle);
        addMouseListeners();

        // add ball
        this.ball = new Ball(180, 250);
        add(ball);
        bounce();

        //add Label
        String str1 = "Game over";
        String str2 = "You win";
        this.label = new GLabel(str1, 0, 0);
        this.label = new GLabel(str2, 0, 0);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (mouseEvent.getX() < CANVAS_WIDTH - paddle.getWidth() / 2 && mouseEvent.getX() > paddle.getWidth() / 2) {
            paddle.setLocation(mouseEvent.getX() - paddle.getWidth() / 2, CANVAS_HEIGHT - paddle.getHeight());
        }
    }

    public void bounce() {
        // start game time
        Instant startTime = Instant.now();
        while (true) {

            if (ball.getRightX() >= CANVAS_WIDTH || ball.getX() <= 0) {
                ball.switchXVelocity();
            }

            if (ball.getY() <= 0) {
                ball.switchYVelocity();
            }

            if (ball.getY() >= CANVAS_HEIGHT) {

                break;
            }

            //add collider
            GObject collider = getCollidingObjects();
            int score;
            if (collider != null && collider.equals(paddle)) {
                ball.switchYVelocity();
            } else if (collider != null) {
                ((Bricks) collider).removeBricks(getCollidingPoint());
                ball.switchYVelocity();
                score = bricks.countBricks();
                if (score == 0) {
                    winGame();
                    this.remove(ball);
                }
            }
            Instant endTime = Instant.now();
            long numberOfSeconds = (endTime.toEpochMilli() - startTime.toEpochMilli()) / 1000;
            int pause = (int) (MAX_PAUSE - numberOfSeconds / 4);
            if (pause < MIN_PAUSE) {
                pause = MIN_PAUSE;
            }
            ball.ballMove();
            pause(pause);
        }
        gameOver();
        this.remove(ball);
        this.remove(paddle);
        this.remove(bricks);
    }

    private GPoint getCollidingPoint() {
        if (getElementAt(ball.getX(), ball.getY()) != null) {
            return new GPoint(ball.getX(), ball.getY());
        } else if (getElementAt(ball.getRightX(), ball.getY()) != null) {
            return new GPoint(ball.getRightX(), ball.getY());
        } else if (getElementAt(ball.getRightX(), ball.getBottomY()) != null) {
            return new GPoint(ball.getRightX(), ball.getBottomY());
        } else if (getElementAt(ball.getX(), ball.getBottomY()) != null) {
            return new GPoint(ball.getX(), ball.getBottomY());
        } else
            return null;
    }

    private GObject getCollidingObjects() {
        if (getElementAt(ball.getX(), ball.getY()) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt(ball.getRightX(), ball.getY()) != null) {
            return getElementAt(ball.getRightX(), ball.getY());
        } else if (getElementAt(ball.getRightX(), ball.getBottomY()) != null) {
            return getElementAt(ball.getRightX(), ball.getBottomY());
        } else if (getElementAt(ball.getX(), ball.getBottomY()) != null) {
            return getElementAt(ball.getX(), ball.getBottomY());
        } else
            return null;
    }

    public void gameOver() {
        this.label = new GLabel("GAME OVER", CANVAS_WIDTH / 2. - paddle.getWidth(), CANVAS_HEIGHT / 2.);
        this.add(label);
    }

    public void winGame() {
        this.label = new GLabel("YOU WIN!", CANVAS_WIDTH / 2. - paddle.getWidth(), CANVAS_HEIGHT / 2.);
        this.add(label);
    }
}



