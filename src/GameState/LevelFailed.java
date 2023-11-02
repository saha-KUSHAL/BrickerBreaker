package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Utils.Load;
import Game.Game;
import GameEntity.Ball;
import GameEntity.BrickLoader;
import GameEntity.Paddle;

public class LevelFailed {

	BufferedImage text, retry, homeButton;
	private int homeButtonX, homeButtonY, retryButtonX, retryButtonY;
	private boolean isMouseClicked;
	private Point mousePoint;

	public LevelFailed() {
		text = Load.LoadImage(Load.LevelFailed);
		retry = Load.LoadImage(Load.Retry);
		retryButtonX = (Game.Width - retry.getWidth()) / 2;
		retryButtonY = 586;
		homeButton = Load.LoadImage(Load.HomeButton);
		homeButtonX = (Game.Width - homeButton.getWidth() - 20);
		homeButtonY = homeButton.getHeight() - 30;
	}

	public void update() {
		if (isMouseClicked) {
			if (getRect(homeButton, homeButtonX, homeButtonY).contains(mousePoint)) {
				GameState.state = GameState.MENU;
				reset();
			}
			if (getRect(retry, retryButtonX, retryButtonY).contains(mousePoint)) {
				PlayState.state = PlayState.Playing;
				reset();
			}
		}

	}

	public void render(Graphics2D g) {
		g.drawImage(text, (Game.Width - text.getWidth()) / 2, (Game.Hight - text.getHeight()) / 2, text.getWidth(),
				text.getHeight(), null);
		g.drawImage(retry, retryButtonX, retryButtonY, retry.getWidth(), retry.getHeight(), null);
		g.drawImage(homeButton, homeButtonX, homeButtonY, homeButton.getWidth(), homeButton.getHeight(), null);
		Toolkit.getDefaultToolkit().sync();
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
		PlayState.state = PlayState.Playing;
	}
}
