package sounds;

import java.io.File;

import java.io.IOException;

import java.util.Scanner;

import javax.sound.sampled.*;
public class SoundLoader {
    Clip clip;
    public static String paddleHit = "res/soundEffects/paddleHit.wav";
    public Clip getClip(String audioEffect){
        AudioInputStream audioInputStream = getAudioStream(audioEffect);
        try {
            return AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFile(String file){
        return new File(file);
    }
    public AudioInputStream getAudioStream(String audioEffect){
        try {
            return AudioSystem.getAudioInputStream(getFile(audioEffect));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
