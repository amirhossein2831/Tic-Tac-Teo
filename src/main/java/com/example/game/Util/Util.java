package com.example.game.Util;

import com.example.game.Application;
import com.example.game.OneVsOneController;
import com.example.game.Exception.EmptyFieldException;
import com.example.game.Exception.NotChooseSideException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Util {
    public static boolean vsComputer = false;
    public static final String MUSIC_PATH = "/home/amirhossein/IdeaProjects/Game/src/main/resources/com/example/game/sound/sound.wav";
    public static final String MUSIC_PATH_WIN = "/home/amirhossein/IdeaProjects/Game/src/main/resources/com/example/game/sound/win.wav";

    public static void goTo(MouseEvent event,String fxName,TextField yourName,TextField opponentName,RadioButton x,boolean vsCom) {
        if (vsCom) {
            vsComputer = true;
        }
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxName + ".fxml"));
            root = loader.load();
            if (x != null) {
                OneVsOneController oneVsOneController = loader.getController();
                oneVsOneController.setYourName(yourName.getText());
                oneVsOneController.setOpponentName(opponentName.getText());
                oneVsOneController.setChoose(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Tic Tac Toe");
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public static void alert(TextField yourName, TextField componentName, RadioButton x,RadioButton o) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (yourName.getText().equals("") || componentName.getText().equals("")) {
            alert.setContentText("one of the name field is empty!!");
            alert.show();
            throw new EmptyFieldException();
        } else if (!x.isSelected() && !o.isSelected()) {
            alert.setContentText("please choose your side (x or o)");
            alert.show();
            throw new NotChooseSideException();
        }
    }
}

