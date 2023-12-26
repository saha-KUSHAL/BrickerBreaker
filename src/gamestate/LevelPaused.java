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

public class LevelPaused {

    private BufferedImage text, resumeButton, exitButton, bg;
    private Point mousePoint;
    private boolean isMouseClicked;

    public LevelPaused() {
        text = Load.LoadImage(Load.PausedText);
        resumeButton = Load.LoadImage(Load.ResumeButton);
        exitButton = Load.LoadImage(Load.ExitButton);
        bg = Load.LoadImage(Load.PausedBg);
    }

    void update() {
        if (isMouseClicked) {
            if (getRect(resumeButton, (Game.Width - resumeButton.getWidth()) / 2, 416).contains(mousePoint)) {
                isMouseClicked = false;
                PlayState.state = PlayState.Playing;
            }
            if (getRect(exitButton, (Game.Width - resumeButton.getWidth()) / 2, 501).contains(mousePoint)) {
                reset();
                PlayState.state = PlayState.Playing;
                Menu.resetBooleans();   // Resetting the mouse event booleans
                GameState.state = GameState.MENU;
                if(LevelManager.getTempLevel() > LevelManager.getLevel())
                    LevelManager.setLevel(LevelManager.getTempLevel());
            }
        }
    }

    void render(Graphics2D g) {
        g.drawImage(bg, 0, 0, bg.getWidth(), bg.getHeight(), null);
        g.drawImage(text, 274, 175, text.getWidth(), text.getHeight(), null);
        g.drawImage(resumeButton, (Game.Width - resumeButton.getWidth()) / 2, 416, resumeButton.getWidth(),
                resumeButton.getHeight(), null);
        g.drawImage(exitButton, (Game.Width - resumeButton.getWidth()) / 2, 501, exitButton.getWidth(),
                exitButton.getHeight(), null);
    }

    private Rectangle getRect(BufferedImage image, int x, int y) {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    protected void mouseClicked(MouseEvent e) {
        mousePoint = new Point(e.getX(), e.getY());
        isMouseClicked = true;
    }

    private void reset() {
        isMouseClicked = false;
        Ball.reset();
        BrickLoader.reset();
        Paddle.reset();
    }
}
