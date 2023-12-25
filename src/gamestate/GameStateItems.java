package gamestate;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameStateItems {
    protected Point mousePoint;
    protected boolean isMouseClicked;

    public void mouseClicked(MouseEvent e) {
        isMouseClicked = true;
        mousePoint = new Point(e.getX(), e.getY());
    }
    protected Rectangle getRect(BufferedImage image, int x, int y) {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
}
