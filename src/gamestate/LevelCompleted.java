package gamestate;

import game.Game;
import gameentity.Ball;
import gameentity.BrickLoader;
import gameentity.Paddle;
import level.LevelManager;
import utils.Load;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class LevelCompleted {
    BufferedImage text, next, homeButton;
    private int homeButtonX, homeButtonY, nextButtonX, nextButtonY;
    private boolean isMouseClicked;
    private Point mousePoint;

    public LevelCompleted() {
        text = Load.LoadImage(Load.LevelCompleted);
        next = Load.LoadImage(Load.NextLevel);
        nextButtonX = (Game.Width - next.getWidth()) / 2;
        nextButtonY = 586;
        homeButton = Load.LoadImage(Load.HomeButton);
        homeButtonX = (Game.Width - homeButton.getWidth() - 20);
        homeButtonY = homeButton.getHeight() - 30;
    }

    private Rectangle getRect(BufferedImage image, int x, int y) {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    protected void mouseClicked(MouseEvent e) {
        mousePoint = new Point(e.getX(), e.getY());
        isMouseClicked = true;
    }

    public void reset() {
        Ball.reset();
        isMouseClicked = false;
        BrickLoader.reset();
        Paddle.reset();
        Menu.resetBooleans();
    }

    public void update() {
        if (isMouseClicked) {
            if (getRect(next, nextButtonX, nextButtonY).contains(mousePoint)) {
                LevelManager.incrementLevel();
                PlayState.state = PlayState.Playing;
                reset();
            }
            if (getRect(homeButton, homeButtonX, homeButtonY).contains(mousePoint)) {
                LevelManager.incrementLevel();
                PlayState.state = PlayState.Playing;
                GameState.state = GameState.MENU;
                reset();
            }
        }
    }

    public void render(Graphics2D g) {
        g.drawImage(homeButton, homeButtonX, homeButtonY, homeButton.getWidth(), homeButton.getHeight(), null);
        g.drawImage(text, (Game.Width - text.getWidth()) / 2, (Game.Hight - text.getHeight()) / 2, text.getWidth(),
                text.getHeight(), null);
        g.drawImage(next, nextButtonX, nextButtonY, next.getWidth(), next.getHeight(), null);
    }
}
