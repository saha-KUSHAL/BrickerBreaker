package Level;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Utils.Load;

public class LevelLoader {
	private BufferedImage levelMap1, levelMap2, levelMap3, levelMap4, levelMap5;
	private int[][] level1, level2, level3, level4, level5;

	public LevelLoader() {
		loadLevel();
	}

	public void loadLevel() {
		levelMap1 = Load.LoadImage(Load.Level1);
		level1 = new int[levelMap1.getHeight()][levelMap1.getWidth()];
		loadBrickIndex(level1, levelMap1);

		levelMap2 = Load.LoadImage(Load.Level2);
		level2 = new int[levelMap2.getHeight()][levelMap2.getWidth()];
		loadBrickIndex(level2, levelMap2);

		levelMap3 = Load.LoadImage(Load.Level3);
		level3 = new int[levelMap3.getHeight()][levelMap3.getWidth()];
		loadBrickIndex(level3, levelMap3);

		levelMap4 = Load.LoadImage(Load.Level4);
		level4 = new int[levelMap4.getHeight()][levelMap4.getWidth()];
		loadBrickIndex(level4, levelMap4);
		levelMap5 = Load.LoadImage(Load.Level5);
		level5 = new int[levelMap5.getHeight()][levelMap5.getWidth()];
		loadBrickIndex(level5, levelMap5);
	}

	private void loadBrickIndex(int[][] level, BufferedImage levelMap) {
		for (int i = 0; i < levelMap.getHeight(); i++) {
			for (int j = 0; j < levelMap.getWidth(); j++) {
				Color color = new Color(levelMap.getRGB(j, i));
				level[i][j] = color.getRed();
			}
		}
	}

	public int[][] getBrickIndex(int level) {
		switch (level) {
		case 1:
			return level1;
		case 2:
			return level2;
		case 3:
			return level3;
		case 4:
			return level4;
		case 5:
			return level5;
		}
		return null;
	}

}
