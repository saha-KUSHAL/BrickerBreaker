package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import Game.Game;
import Utils.Load;

public class Menu extends GameStateItems {

	private BufferedImage[] buttons;
	private BufferedImage playButton;
	private final int playButtonX = 388;
	private final int playButtonY = 481;
	private final int MenuBarX = 0;
	private final int MenuBarY = 570;
	private final int LevelX = 315;
	private final int LevelY = 606;
	private final int SettingX = 723;
	private final int SettingY = 607;
	private static Boolean mousePressed;
	private Boolean mouseOver;
	private static Boolean mouseReleased;

	public Menu() {
		buttons = new BufferedImage[4];
		buttons[0] = Load.LoadImage(Load.Menubar);
		buttons[1] = Load.LoadImage(Load.SettingsButton);
		buttons[2] = Load.LoadImage(Load.LevelButton);
		buttons[3] = Load.LoadImage(Load.Logo);
		playButton = Load.LoadImage(Load.PlayButton);
		mousePressed = mouseOver = mouseReleased = false;

	}

	public void update() {
		if (isMouseClicked) {
			isMouseClicked = false;
			if (getRect(3).contains(mousePoint)) {
				GameState.state = GameState.PLAYING;
			}
			if (getRect(1).contains(mousePoint))
				GameState.state = GameState.SETTING;
			if (getRect(2).contains(mousePoint))
				GameState.state = GameState.LEVEL;

		}
	}

	public void render(Graphics2D g) {
		g.drawImage(buttons[0], MenuBarX, MenuBarY, buttons[0].getWidth(), buttons[0].getHeight(), null);
		g.drawImage(buttons[1], SettingX, SettingY, buttons[1].getWidth(), buttons[1].getHeight(), null);
		g.drawImage(buttons[2], LevelX, LevelY, buttons[2].getWidth(), buttons[2].getHeight(), null);
		g.drawImage(buttons[3], (int) (Game.Width - buttons[3].getWidth()) / 2, 0, buttons[3].getWidth(),
				buttons[3].getHeight(), null);

		if(!mousePressed)
			g.drawImage(playButton.getSubimage(0, 0, playButton.getWidth() / 3, playButton.getHeight()), playButtonX,
				playButtonY, playButton.getWidth() / 3, playButton.getHeight(), null);
		if (mouseOver && !mousePressed)
			g.drawImage(playButton.getSubimage(245, 0, playButton.getWidth() / 3, playButton.getHeight()), playButtonX,
					playButtonY, playButton.getWidth() / 3, playButton.getHeight(), null);
		
		if (mousePressed)
			g.drawImage(playButton.getSubimage(490, 0, playButton.getWidth() / 3, playButton.getHeight()), playButtonX,
					playButtonY, playButton.getWidth() / 3, playButton.getHeight(), null);
	}

	private Rectangle getRect(int index) {
		Rectangle rectangle = null;
		switch (index) {
		case 0:
			rectangle = new Rectangle(MenuBarX, MenuBarY, buttons[index].getWidth(), buttons[index].getHeight());
			break;
		case 1:
			rectangle = new Rectangle(SettingX, SettingY, buttons[index].getWidth(), buttons[index].getHeight());
			break;
		case 2:
			rectangle = new Rectangle(LevelX, LevelY, buttons[index].getWidth(), buttons[index].getHeight());
			break;
		case 3:
			rectangle = new Rectangle(playButtonX, playButtonY, playButton.getWidth() / 3, playButton.getHeight());
		}
		return rectangle;
	}

	public static void resetBooleans() {
		mousePressed = mouseReleased = false;
	}

	public void mousePressed(MouseEvent e) {
		if (getRect(3).contains(e.getPoint()))
			mousePressed = true;
	}

	public void mouseReleased(MouseEvent e) {
		if (getRect(3).contains(e.getPoint()))
			mouseReleased = true;
	}

	public void mouseMoved(MouseEvent e) {
		if (getRect(3).contains(e.getPoint()))
			mouseOver = true;
		else
			mouseOver = false;
	}
	
}
