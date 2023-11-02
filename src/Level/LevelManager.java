package Level;

public class LevelManager {
	
	private static int currentLevel =1;

	public static void setLevel(int level) {
		currentLevel = level;
	}
	
	public static int getLevel() {
		return currentLevel;
	}

	public static void incrementLevel() {
		currentLevel++;
	}
}
