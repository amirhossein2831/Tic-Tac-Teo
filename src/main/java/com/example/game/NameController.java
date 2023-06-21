package com.example.game;

import com.example.game.Exception.EmptyFieldException;
import com.example.game.Exception.NotChooseSideException;
import com.example.game.Media.MediaPlayer;
import com.example.game.Util.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class NameController implements Initializable {

    @FXML
    private TextField ComponentName_TF;

    @FXML
    private RadioButton o_RB;

    @FXML
    private RadioButton x_RB;

    @FXML
    private TextField yourName_TF;

    @FXML
    void backToApp(MouseEvent event) {
        Util.vsComputer = false;
        Util.goTo(event,"application",null,null,null,false);
    }

    @FXML
    void startGame(MouseEvent event) {
        try {
            Util.alert(yourName_TF, ComponentName_TF, x_RB, o_RB);
        } catch (EmptyFieldException | NotChooseSideException e) {
            System.out.println(e.getMessage());
            return;
        }
        MediaPlayer.audio.setMicrosecondPosition(0);
        if (Util.vsComputer) {
            Util.goTo(event,"vsComputer",yourName_TF,ComponentName_TF,x_RB,false);
        } else
            Util.goTo(event,"oneVsOne",yourName_TF,ComponentName_TF,x_RB,false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
