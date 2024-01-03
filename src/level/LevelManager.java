package level;

public class LevelManager {

    private static int currentLevel = 1;
    private static int tempLevel;

    public static int getLevel() {
        return currentLevel;
    }

    public static void setLevel(int level) {
        currentLevel = level;
    }

    public static void incrementLevel() {
        currentLevel++;
    }
    public static void setTempLevel(int level) {
        tempLevel = level;
    }

    public static int getTempLevel(){

        return tempLevel;
    }
}
