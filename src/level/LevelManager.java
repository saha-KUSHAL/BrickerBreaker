package level;

public class LevelManager {

    private static int currentLevel = 4;

    public static int getLevel() {
        return currentLevel;
    }

    public static void setLevel(int level) {
        currentLevel = level;
    }

    public static void incrementLevel() {
        currentLevel++;
    }
}
