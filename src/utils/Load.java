package utils;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Load {

    public static final String PlayButton = "/icons/PlayButtons.png";
    public static final String SettingsButton = "/icons/Settings.png";
    public static final String Menubar = "/icons/MenuBar.png";
    public static final String LevelButton = "/icons/Level.png";
    public static final String Logo = "/icons/Logo.png";
    public static final String HomeButton = "/icons/HomeButton.png";

    public static final String Level1 = "/levels/1.png";
    public static final String Level2 = "/levels/2.png";
    public static final String Level3 = "/levels/3.png";
    public static final String Level4 = "/levels/4.png";
    public static final String Level5 = "/levels/5.png";

    public static final String LevelFailed = "/icons/LevelFailed.png";
    public static final String Retry = "/icons/Retry.png";
    public static final String LevelCompleted = "/icons/LevelCompleted.png";
    public static final String NextLevel = "/icons/NextLevel.png";

    public static final String LevelCompletedBg = "/bg/LevelCompletedBg.png";
    public static final String LevelText = "/icons/LevelText.png";
    public static final String LevelIncompletedBg = "/bg/LevelIncompletedBg.png";
    public static final String BackButton = "/icons/BackButton.png";
    public static final String LevelIndicator = "/icons/LevelIndicator.png";
    public static final String ScoreBg = "/bg/ScoreBg.png";
    public static final String LevelTextBg = "/bg/LevelTextBg.png";
    public static final String PausedBg = "/bg/PausedBg.png";
    public static final String ResumeButton = "/icons/Resume.png";
    public static final String ExitButton = "/icons/Exit.png";
    public static final String PausedText = "/icons/PausedText.png";
    public static final String PauseIcon = "/icons/PauseIcon.png";
    public static final String ContinueIcon = "/icons/ContinueIcon.png";
    //public static final String FontAltRivira = "/ALTRiviera-Regular.otf";
    public static final String LogoIcon = "/icons/LogoIcon.png";
    public static final String BackButton1 = "/icons/BackButton1.png";
    public static final String AboutText = "/icons/aboutText.png";

    public static BufferedImage LoadImage(String file) {
        BufferedImage img = null;
        InputStream link = Load.class.getResourceAsStream(file);
        try {
            img = ImageIO.read(link);
        } catch (IOException e) {
            if (Game.debug) {
                System.err.println("Can't read image: " + file);
                e.printStackTrace();
            }
        }
        return img;
    }

    //public static InputStream LoadFont(String font) {

    //}

}
