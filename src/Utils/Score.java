package Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import GameEntity.BrickLoader;
import Level.LevelManager;

public class Score {
	private String score,level;
	private BufferedImage levelBg,scoreBg;
	public Score() {
		score = "Score : " + 0;
		scoreBg = Load.LoadImage(Load.ScoreBg);
		levelBg = Load.LoadImage(Load.LevelTextBg);
	}
	
	public void update() {
		score = "Score : " + BrickLoader.Score;
		level = "Level : " + LevelManager.getLevel();
	}
	
	public void render(Graphics2D g) {
		g.drawImage(scoreBg,0,20,scoreBg.getWidth(),levelBg.getHeight(),null);
		g.drawImage(levelBg,898,20,levelBg.getWidth(),levelBg.getHeight(),null);
		g.setColor(Color.WHITE);
		Font f = new Font("Titan One",Font.PLAIN,20);
		g.setFont(f);
		g.drawString(score,20,50);
		g.drawString(level,980,50);

	}
}

