package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Game.Game;
import GameEntity.Ball;
import GameEntity.BrickLoader;
import GameEntity.Paddle;
import Level.LevelManager;
import Utils.Load;

public class Level extends GameStateItems {
	private BufferedImage text, LevelCompletedBg, LevelIncompletedBg, backButton,lvlIndicator;
	private BufferedImage lvl[];
	private int textX, textY, LvlComBgY, backButtonX, backButtonY, lvlButtonWidth, lvlButtonHight,
	lvlIndicatorY;
	// private File fontFile;
	private Font customFont;
	private Color comFontColor, inComFontColor;
	private int lvlBgXCord[], clickedLevel, curLevel;

	public Level() {
		comFontColor = new Color(255, 247, 214);
		inComFontColor = new Color(54, 75, 94);
		customFont = new Font("ALT Riviera", Font.PLAIN, 84);
		clickedLevel=0;
		text = Load.LoadImage(Load.LevelText);
		textX = (Game.Width - text.getWidth()) / 2;
		textY = 106;

		backButton = Load.LoadImage(Load.BackButton);
		backButtonX = (Game.Width - backButton.getWidth() - 20);
		backButtonY = backButton.getHeight() - 30;

		LevelCompletedBg = Load.LoadImage(Load.LevelCompletedBg);
		LevelIncompletedBg = Load.LoadImage(Load.LevelIncompletedBg);
		LvlComBgY = 373;
		lvlButtonHight = LevelCompletedBg.getHeight();
		lvlButtonWidth = LevelCompletedBg.getWidth();

		lvl = new BufferedImage[2];
		lvl[0] = Load.LoadImage(Load.LevelCompletedBg);
		lvl[1] = Load.LoadImage(Load.LevelIncompletedBg);
		lvlIndicator = Load.LoadImage(Load.LevelIndicator);
		lvlIndicatorY = 345;
		lvlBgXCord = new int[] { 118, 291, 465, 638, 812 };
	}

	public void update() {
		curLevel = LevelManager.getLevel();
		if (isMouseClicked) {
			if (getRect(backButton, backButtonX, backButtonY).contains(mousePoint)) {
				GameState.state = GameState.MENU;
				reset();
			}
			if (getRect(LevelCompletedBg, lvlBgXCord[0], LvlComBgY).contains(mousePoint))
				clickedLevel = 1;
			if (getRect(LevelCompletedBg, lvlBgXCord[1], LvlComBgY).contains(mousePoint))
				clickedLevel = 2;
			if (getRect(LevelCompletedBg, lvlBgXCord[2], LvlComBgY).contains(mousePoint))
				clickedLevel = 3;
			if (getRect(LevelCompletedBg, lvlBgXCord[3], LvlComBgY).contains(mousePoint))
				clickedLevel = 4;
			if (getRect(LevelCompletedBg, lvlBgXCord[4], LvlComBgY).contains(mousePoint))
				clickedLevel = 5;
			if (clickedLevel <= curLevel && clickedLevel != 0) {
				reset();
				LevelManager.setLevel(clickedLevel);
				Ball.reset();
				BrickLoader.reset();
				Paddle.reset();
				GameState.state = GameState.PLAYING;
			}
		}
	}

	private Rectangle getRect(BufferedImage image, int x, int y) {
		return new Rectangle(x, y, image.getWidth(), image.getHeight());
	}

	public void reset() {
		isMouseClicked = false;
	}

	public void render(Graphics2D g) {
		g.drawImage(text, textX, textY, text.getWidth(), text.getHeight(), null);
		g.drawImage(backButton, backButtonX, backButtonY, backButton.getWidth(), backButton.getHeight(), null);
		g.setFont(customFont);
		g.setColor(comFontColor);
		switch (curLevel - 1) {
		case 5:
			g.drawImage(LevelCompletedBg, lvlBgXCord[4], LvlComBgY, lvlButtonWidth, lvlButtonHight, null);
			g.drawString("5", lvlBgXCord[4] + lvlButtonWidth / 3, 515);
		case 4:
			g.drawImage(LevelCompletedBg, lvlBgXCord[3], LvlComBgY, lvlButtonWidth, lvlButtonHight, null);
			g.drawString("4", lvlBgXCord[3] + lvlButtonWidth / 3, 515);
		case 3:
			g.drawImage(LevelCompletedBg, lvlBgXCord[2], LvlComBgY, lvlButtonWidth, lvlButtonHight, null);
			g.drawString("3", lvlBgXCord[2] + lvlButtonWidth / 3, 515);
		case 2:
			g.drawImage(LevelCompletedBg, lvlBgXCord[1], LvlComBgY, lvlButtonWidth, lvlButtonHight, null);
			g.drawString("2", lvlBgXCord[1] + lvlButtonWidth / 3, 515);
		case 1:
			g.drawImage(LevelCompletedBg, lvlBgXCord[0], LvlComBgY, lvlButtonWidth, lvlButtonHight, null);
			g.drawString("1", lvlBgXCord[0] + lvlButtonWidth / 3, 515);
		}
		g.setColor(inComFontColor);
		switch (curLevel) {
		case 1:
			g.drawImage(LevelIncompletedBg, lvlBgXCord[0], LvlComBgY, lvlButtonWidth, lvlButtonHight, null);
			g.drawString("1", lvlBgXCord[0] + lvlButtonWidth / 3, 515);
		case 2:
			g.drawImage(LevelIncompletedBg, lvlBgXCord[1], LvlComBgY, lvlButtonWidth, lvlButtonHight, null);
			g.drawString("2", lvlBgXCord[1] + lvlButtonWidth / 3, 515);
		case 3:
			g.drawImage(LevelIncompletedBg, lvlBgXCord[2], LvlComBgY, lvlButtonWidth, lvlButtonHight, null);
			g.drawString("3", lvlBgXCord[2] + lvlButtonWidth / 3, 515);
		case 4:
			g.drawImage(LevelIncompletedBg, lvlBgXCord[3], LvlComBgY, lvlButtonWidth, lvlButtonHight, null);
			g.drawString("4", lvlBgXCord[3] + lvlButtonWidth / 3, 515);
		case 5:
			g.drawImage(LevelIncompletedBg, lvlBgXCord[4], LvlComBgY, lvlButtonWidth, lvlButtonHight, null);
			g.drawString("5", lvlBgXCord[4] + lvlButtonWidth / 3, 515);
		}
		
		switch(curLevel) {
		case 1:
			g.drawImage(lvlIndicator,(lvlBgXCord[curLevel-1] + lvlButtonWidth / 2)- 13,lvlIndicatorY,null);
			break;
		case 2:
			g.drawImage(lvlIndicator,(lvlBgXCord[curLevel-1] + lvlButtonWidth / 2)- 13,lvlIndicatorY,null);
			break;
		case 3:
			g.drawImage(lvlIndicator,(lvlBgXCord[curLevel-1] + lvlButtonWidth / 2)- 13,lvlIndicatorY,null);
			break;
		case 4:
			g.drawImage(lvlIndicator,(lvlBgXCord[curLevel-1] + lvlButtonWidth / 2)- 13,lvlIndicatorY,null);
			break;
		case 5:
			g.drawImage(lvlIndicator,(lvlBgXCord[curLevel-1] + lvlButtonWidth / 2)- 13,lvlIndicatorY,null);
			break;
		}
	}

}
