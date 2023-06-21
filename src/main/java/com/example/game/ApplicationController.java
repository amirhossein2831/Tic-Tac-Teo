package com.example.game;

import com.example.game.Util.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    @FXML
    void ExitClick(MouseEvent event) {
        Platform.exit();
    }
    @FXML
    void vsComputer(MouseEvent event) {
        Util.goTo(event, "name",null,null,null,true);
    }

    @FXML
    void vsOne(MouseEvent event) {
        Util.goTo(event, "name",null,null,null,false);
    }
}
