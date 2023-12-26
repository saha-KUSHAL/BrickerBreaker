package gameentity;

import game.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Ball extends Entity implements GameEntity {

    public static boolean isMouseClicked = false;
    private static int hight, width;
    private static int dx;
    private static int dy;
    private static int x;
    private static int y;
    private static int xTemp;
    private static int yTemp;
    private static boolean isAlive;
    private boolean topLeft, topRight, bottomLeft, bottomRight;

    public Ball() {
        hight = 15;
        width = 15;
        dx = (int) (2 * Game.scale);
        dy = (int) (-1 * Game.scale);
        x = (Game.Width - width) / 2;
        y = Game.Hight - 100;
        isAlive = true;
    }

    public static void reset() {
        dx = (int) (2 * Game.scale);
        dy = (int) (-1 * Game.scale);
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

    public Boolean checkCollision(Rectangle r) {
        boolean isCollided = false;
        int xDest = x + dx;
        int yDest = y + dy;
        xTemp = x;
        yTemp = y;

        //  Checking  horizontal collision
        checkCorners(xDest, y, r);

        // Traveling right
        if (dx > 0 && (topRight || bottomRight)) {
            dx = -dx;
            isCollided = true;
            xTemp = (int) r.getMinX() - width - 1;
        }
        //Traveling left
        if (dx < 0 && (topLeft && bottomLeft)) {
            dx = -dx;
            isCollided = true;
            xTemp = (int) r.getMaxX() + 1;
        }

        //Checking Vertical Collison
        checkCorners(x, yDest, r);

        // Traveling to bottom
        if (dy > 0 && (bottomRight || bottomLeft)) {
            dy = -dy;
            isCollided = true;
            yTemp = (int) r.getMinY() - hight - 1;
            System.out.printf("BottomRight, BottomLeft, yTemp = %d\n",yTemp);
        }
        //Traveling to top
        if (dy < 0 && (topLeft || topRight)) {
            dy = -dy;
            isCollided = true;
            yTemp = (int) r.getMaxY() + 1;
            System.out.printf("TopRight, TopLeft, yTemp = %d\n",yTemp);
        }
        return isCollided;
    }

    private void checkCorners(int x, int y, Rectangle r) {
        topLeft = topRight = bottomLeft = bottomRight = false;
        topLeft = r.contains(new Point(x, y));
        bottomLeft = r.contains(new Point(x, y + hight));
        topRight = r.contains(new Point(x + width, y));
        bottomRight = r.contains(new Point(x + width, y + hight));
    }


    @Override
    public void update() {
        if (isAlive) {
            if (x <= 0 || x >= Game.Width - width)
                dx = -dx;
            if (y <= 0)
                dy = -dy;
            if (y > Game.Hight)
                isAlive = false;
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
