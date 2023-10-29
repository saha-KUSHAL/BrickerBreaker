package Level;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Utils.Load;

public class LevelLoader {
	private BufferedImage levelImage1;
	private int[][] level1;
	
	public LevelLoader() {
		loadLevel();
	}
	public void loadLevel() {

			levelImage1 = Load.LoadImage(Load.Level1);
			level1 = new int[levelImage1.getHeight()][levelImage1.getWidth()];
			loadBrickIndex(level1);
	}

	private void loadBrickIndex(int[][] level) {
		for (int i = 0; i < levelImage1.getHeight(); i++) {
			for (int j = 0; j < levelImage1.getWidth(); j++) {
				Color color = new Color(levelImage1.getRGB(j,i));
				level[i][j] = color.getRed();
			}
		} 
	}

	public int[][] getBrickIndex(int level) {
		switch (level) {
		case 1:
			return level1;
		}
		return null;
	}

}
