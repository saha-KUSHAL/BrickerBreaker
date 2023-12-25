package utils;

import gameentity.BrickLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Score {
    private String score;
    private BufferedImage levelBg, scoreBg;

    public Score() {
        score = "Score : " + 0;
        scoreBg = Load.LoadImage(Load.ScoreBg);
        levelBg = Load.LoadImage(Load.LevelTextBg);
    }

    public void update() {
        score = "Score : " + BrickLoader.Score;
    }

    public void render(Graphics2D g) {
        g.drawImage(scoreBg, 0, 20, scoreBg.getWidth(), scoreBg.getHeight(), null);
        g.drawImage(levelBg, 898, 20, levelBg.getWidth(), levelBg.getHeight(), null);
        g.setColor(Color.WHITE);
        Font f = new Font("Titan One", Font.PLAIN, 20);
        g.setFont(f);
        g.drawString(score, 20, 50);

    }
}

