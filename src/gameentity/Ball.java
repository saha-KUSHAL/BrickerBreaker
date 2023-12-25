package gameentity;

import game.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Ball extends Entity implements GameEntity {

    public static boolean isMouseClicked = false;
    private static int hight, width;
    private static double dx, dy, x, y;
    private static boolean isAlive;
    private Paddle paddle;
    private boolean topLeft, topRight, bottomLeft, bottomRight, isCollided;
    private double xTemp, yTemp;

    public Ball(Paddle paddle) {
        this.paddle = paddle;
        hight = 20;
        width = 20;
        dx = 2 * Game.scale;
        dy = -1 * Game.scale;
        x = (Game.Width - width) / 2;
        y = Game.Hight - 100;
        isAlive = true;
    }

    public static void reset() {
        dx = 2 * Game.scale;
        dy = -1 * Game.scale;
        x = (Game.Width - width) / 2;
        y = Game.Hight - 100;
        isAlive = true;
        isMouseClicked = false;
    }

    public static boolean getALive() {
        return isAlive;
    }

    @Override
    public Rectangle getHitbox() {

        return new Rectangle((int) x, (int) y, width, hight);
    }

    public void setPoition() {
        xTemp += dx;
        yTemp += dy;
        x = xTemp;
        y = yTemp;
    }

    protected void checkCollision(Rectangle r) {
        isCollided = false;
        double xDest = x + dx;
        double yDest = y + dy;
        xTemp = x;
        yTemp = y;
        //  Checking  horizontal collision
        checkCorners((int) xDest, (int) y, r);

        // Traveling right
        if (dx > 0 && (topRight || bottomRight)) {
            dx = -dx;
            isCollided = true;
            xTemp = r.getMinX() - width - 1;
        }
        //Traveling left
        if (dx < 0 && (topLeft && bottomLeft)) {
            dx = -dx;
            isCollided = true;
            xTemp = r.getMaxX() + 1;
        }

        //Checking Vertical Collison
        checkCorners((int) x, (int) yDest, r);

        // Traveling to bottom
        if (dy > 0 && (bottomRight || bottomLeft)) {
            dy = -dy;
            isCollided = true;
            yTemp = r.getMinY() - hight - 1;
        }
        //Traveling to top
        if (dy < 0 && (topLeft || topRight)) {
            dy = -dy;
            isCollided = true;
            yTemp = r.getMaxX() + 1;
        }
    }

    private void checkCorners(int x, int y, Rectangle r) {
        topLeft = topRight = bottomLeft = bottomRight = isCollided = false;
        topLeft = r.contains(new Point(x, y));
        bottomLeft = r.contains(new Point(x, y + hight));
        topRight = r.contains(new Point(x + width, y));
        bottomRight = r.contains(new Point(x + width, y + hight));
    }

    protected boolean isCollided() {
        return isCollided;
    }

    @Override
    public void update() {
        if (isMouseClicked && isAlive) {
            if (x < 0 || x >= Game.Width - width)
                dx = -dx;
            if (y <= 0)
                dy = -dy;
            if (y > Game.Hight)
                isAlive = false;
            checkCollision(paddle.getHitbox());
            setPoition();
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (isAlive) {
            g.setColor(Color.orange);
            g.fillOval((int) x, (int) y, 20, 20);
            Toolkit.getDefaultToolkit().sync(); // Flush the Linux graphics buffer
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        isMouseClicked = true;

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
