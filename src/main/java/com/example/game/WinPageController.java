package com.example.game;

import com.example.game.Media.MediaPlayer;
import com.example.game.Util.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinPageController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void exit(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void goMenu(MouseEvent event) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MediaPlayer.audio.stop();
        MediaPlayer.changeMusic(Util.MUSIC_PATH);
        Util.goTo(event,"application",null,null,null,false);
    }

}
