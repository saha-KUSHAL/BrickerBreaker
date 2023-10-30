package Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import GameEntity.BrickLoader;
import Level.LevelManager;

public class Score {
	private String score;
	public Score() {
		score = "Score : " + 0;
	}
	
	public void update() {
		score = "Score : " + BrickLoader.Score + " Level: " + LevelManager.getLevel();
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		Font f = new Font("Inter",Font.BOLD,20);
		g.setFont(f);
		g.drawString(score, 55, 30);
	}
}
