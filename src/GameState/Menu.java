package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Game.Game;
import Utils.Load;

public class Menu extends GameStateItems {

	/*
	 * [0] - menubar [1] - playbutton [2] - setting [4] - level
	 */
	private BufferedImage[] buttons;
	private final int playButtonX = 463;
	private final int playButtonY = 501;
	private final int MenuBarX = 0;
	private final int MenuBarY = 570;
	private final int LevelX = 315;
	private final int LevelY = 606;
	private final int SettingX = 723;
	private final int SettingY = 607;

	public Menu() {
		buttons = new BufferedImage[5];
		buttons[0] = Load.LoadImage(Load.Menubar);
		buttons[1] = Load.LoadImage(Load.PlayButton);
		buttons[2] = Load.LoadImage(Load.SettingsButton);
		buttons[3] = Load.LoadImage(Load.LevelButton);
		buttons[4] = Load.LoadImage(Load.Logo);

	}

	public void update() {
		if (isMouseClicked) {
			isMouseClicked = false;
			if (getRect(1).contains(mousePoint))
				GameState.state = GameState.PLAYING;
			if (getRect(2).contains(mousePoint))
				GameState.state = GameState.SETTING;
			if (getRect(3).contains(mousePoint))
				GameState.state = GameState.LEVEL;
		}
	}

	public void render(Graphics2D g) {
		g.drawImage(buttons[0], MenuBarX, MenuBarY, buttons[0].getWidth(), buttons[0].getHeight(), null);
		g.drawImage(buttons[1], playButtonX, playButtonY, buttons[1].getWidth(), buttons[1].getHeight(), null);
		g.drawImage(buttons[2], SettingX, SettingY, buttons[2].getWidth(), buttons[2].getHeight(), null);
		g.drawImage(buttons[3], LevelX, LevelY, buttons[3].getWidth(), buttons[3].getHeight(), null);
		g.drawImage(buttons[4],(int) (Game.Width - buttons[4].getWidth()) / 2, 0, buttons[4].getWidth(), buttons[4].getHeight(), null);
	}

	private Rectangle getRect(int index) {
		Rectangle rectangle = null;
		switch (index) {
		case 0:
			rectangle = new Rectangle(MenuBarX, MenuBarY, buttons[index].getWidth(), buttons[index].getHeight());
			break;
		case 1:
			rectangle = new Rectangle(playButtonX, playButtonY, buttons[index].getWidth(), buttons[index].getHeight());
			break;
		case 2:
			rectangle = new Rectangle(SettingX, SettingY, buttons[index].getWidth(), buttons[index].getHeight());
			break;
		case 3:
			rectangle = new Rectangle(LevelX, LevelY, buttons[index].getWidth(), buttons[index].getHeight());
			break;
		}
		return rectangle;
	}
}