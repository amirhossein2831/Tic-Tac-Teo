package com.example.game.Media;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class MediaPlayer {
    public static Clip audio;

    public static  void changeMusic(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(path);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        audio = AudioSystem.getClip();
        audio.open(audioInputStream);
        audio.loop(Clip.LOOP_CONTINUOUSLY);
        audio.start();
    }
}
