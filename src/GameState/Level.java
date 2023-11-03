package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Game.Game;
import Level.LevelManager;
import Utils.Load;

public class Level extends GameStateItems {
	private BufferedImage text, LevelCompletedBg, LevelIncompletedBg,backButton;
	private BufferedImage lvl[];
	private int textX, textY, LvlComBgY,backButtonX,backButtonY;
	//private File fontFile;
	private Font customFont;
	private Color comFontColor,inComFontColor;
	public Level() {
		comFontColor = new Color(255,247,214);
		inComFontColor = new Color(54,75,94);
		customFont = new Font("ALT Riviera",Font.PLAIN,84);
		text = Load.LoadImage(Load.LevelText);
		textX = (Game.Width - text.getWidth()) / 2;
		textY = 106;
		backButton = Load.LoadImage(Load.BackButton);
		backButtonX = (Game.Width - backButton.getWidth() - 20);
		backButtonY = backButton.getHeight() - 30;
		LevelCompletedBg = Load.LoadImage(Load.LevelCompletedBg);
		LevelIncompletedBg = Load.LoadImage(Load.LevelIncompletedBg);
		LvlComBgY = 373;
		lvl = new BufferedImage[2];
		lvl[0] = Load.LoadImage(Load.LevelCompletedBg);
		lvl[1] = Load.LoadImage(Load.LevelIncompletedBg);
	}

	public void update() {
		if(isMouseClicked) {
			if(getRect(backButton, backButtonX, backButtonY).contains(mousePoint)) {
				GameState.state = GameState.MENU;
				reset();
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
		g.drawImage(backButton,backButtonX,backButtonY,backButton.getWidth(),backButton.getHeight(),null);
		g.setFont(customFont);
		g.setColor(comFontColor);
		switch (LevelManager.getLevel()-1) {
		case 5:
			g.drawImage(LevelCompletedBg, 812, LvlComBgY, LevelCompletedBg.getWidth(), LevelCompletedBg.getHeight(),
					null);
			g.drawString("5",812 + LevelCompletedBg.getWidth()/3,515);
		case 4:
			g.drawImage(LevelCompletedBg, 638, LvlComBgY, LevelCompletedBg.getWidth(), LevelCompletedBg.getHeight(),
					null);
			g.drawString("4",638 + LevelCompletedBg.getWidth()/3,515);
		case 3:
			g.drawImage(LevelCompletedBg, 465, LvlComBgY, LevelCompletedBg.getWidth(), LevelCompletedBg.getHeight(),
					null);
			g.drawString("3",465 + LevelCompletedBg.getWidth()/3,515);
		case 2:
			g.drawImage(LevelCompletedBg, 291, LvlComBgY, LevelCompletedBg.getWidth(), LevelCompletedBg.getHeight(),
					null);
			g.drawString("2",291 + LevelCompletedBg.getWidth()/3,515);
		case 1:
			g.drawImage(LevelCompletedBg, 118, LvlComBgY, LevelCompletedBg.getWidth(), LevelCompletedBg.getHeight(),
					null);
			g.drawString("1",118 + LevelCompletedBg.getWidth()/3,515);
		}
		g.setColor(inComFontColor);
		switch (LevelManager.getLevel()) {
		case 1:
			g.drawImage(LevelIncompletedBg, 118, LvlComBgY, LevelCompletedBg.getWidth(), LevelCompletedBg.getHeight(),
					null);
			g.drawString("1",118 + LevelCompletedBg.getWidth()/3,515);
		case 2:
			g.drawImage(LevelIncompletedBg, 291, LvlComBgY, LevelIncompletedBg.getWidth(), LevelIncompletedBg.getHeight(),
					null);
			g.drawString("2",291 + LevelCompletedBg.getWidth()/3,515);
		case 3:
			g.drawImage(LevelIncompletedBg, 465, LvlComBgY, LevelIncompletedBg.getWidth(), LevelIncompletedBg.getHeight(),
					null);
			g.drawString("3",465 + LevelCompletedBg.getWidth()/3,515);
		case 4:
			g.drawImage(LevelIncompletedBg, 638, LvlComBgY, LevelIncompletedBg.getWidth(), LevelIncompletedBg.getHeight(),
					null);
			g.drawString("4",638 + LevelCompletedBg.getWidth()/3,515);
		case 5:
			g.drawImage(LevelIncompletedBg, 812, LvlComBgY, LevelIncompletedBg.getWidth(), LevelIncompletedBg.getHeight(),
					null);
			g.drawString("5",812 + LevelCompletedBg.getWidth()/3,515);
		}
		

	}
	
}
