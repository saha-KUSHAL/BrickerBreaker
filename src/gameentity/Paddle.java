package gameentity;

import game.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Paddle extends Entity implements GameEntity {
    private static double paddleX, mouseX, y;
    private static int width;

    public Paddle() {
        hight = 15;
        width = 100;
        dx = 10;
        dy = 0;
        paddleX = (Game.Width - width) / 2;
        mouseX = paddleX;
        y = Game.Hight - 80;
    }

    public static void reset() {
        paddleX = (double) (Game.Width - width) / 2;
        mouseX = paddleX;
        y = Game.Hight - 80;
    }

    @Override
    public void update() {
        paddleX += (mouseX - paddleX - (double) width / 2) * .05;
        if (paddleX >= Game.Width - width)
            paddleX = Game.Width - width;
        if (paddleX <= 0)
            paddleX = 0;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect((int) paddleX, (int) y, width, hight, 15, 15);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle((int) paddleX, (int) y, width, hight);
    }

}
