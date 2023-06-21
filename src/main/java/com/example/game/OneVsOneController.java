package com.example.game;

import com.example.game.Media.MediaPlayer;
import com.example.game.Util.Util;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class OneVsOneController implements Initializable {

    @FXML
    protected Label yourChoose_label;
    @FXML
    private Button whoIsFirst_Button;

    @FXML
    private Label oponentChoose_Label;

    @FXML
    private Label yourName_label;

    @FXML
    private Label opponentName_label;

    @FXML
    private GridPane gridpane;
    @FXML
    private Label turn1;

    @FXML
    private Label turn2;
    protected Button[][] buttons;
    protected int randomNum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        whoIsFirst(whoIsFirst_Button);
        randomNum = new Random().nextInt(2) + 1;
        buttons = new Button[5][5];
        initialButton();
        setBackgroundColor();
        setActionForButtons();
    }

    protected void whoIsFirst(Button button) {
        button.setOnAction(event -> {
            if (randomNum == 1) {
            setTurnLabel(2);
        } else if (randomNum == 2) {
            setTurnLabel(1);
            }
        });
    }

    public void setYourName(String str) {
        yourName_label.setText(str);
    }

    public void setOpponentName(String str) {
        opponentName_label.setText(str);
    }

    public void setChoose(RadioButton x) {
        if (x.isSelected()) {
            yourChoose_label.setText("X");
            yourChoose_label.setStyle("-fx-background-color: #E74916; -fx-background-radius: 10px");
            oponentChoose_Label.setText("O");
            oponentChoose_Label.setStyle("-fx-background-color: #06BFF6;-fx-background-radius: 10px");

        } else {
            oponentChoose_Label.setText("X");
            oponentChoose_Label.setStyle("-fx-background-color: #E74916;-fx-background-radius: 10px");
            yourChoose_label.setText("O");
            yourChoose_label.setStyle("-fx-background-color: #06BFF6;-fx-background-radius: 10px");
        }
        yourChoose_label.setAlignment(Pos.CENTER);
        oponentChoose_Label.setAlignment(Pos.CENTER);
    }

    protected void setBackgroundColor() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf("#2F6A66"), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setBackground(background);
                buttons[i][j].setBorder(new Border(new BorderStroke(
                        Color.BLACK,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
            }
        }
    }

    protected void initialButton() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Button button = new Button();
                buttons[row][col] = button;
                button.setText("");
                button.setPrefWidth(160);
                button.setPrefHeight(147);
                gridpane.add(button, col, row);
            }
        }
    }

    protected void setActionForButtons() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                final Button BUTTON = buttons[i][j];
                buttons[i][j].setOnAction(event -> {
                    try {
                        setBackgroundImageButton(event, BUTTON, buttons);
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    private void setBackgroundImageButton(Event event, Button button, Button[][] buttons) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (button.getText().equals("   ") || button.getText().equals("  ")) {
            return;
        }
        setTurnLabel(randomNum);
        String path = "";
        if (randomNum == 1) {//1 for x and red

            button.setText("   ");
            path = "image/x3.png";
            randomNum = 2;
        } else if (randomNum == 2) {
            button.setText("  ");
            path = "image/o5.png";
            randomNum = 1;
        }
        setBackground(button,path);
        finishCondition(event);
    }

    protected void finishCondition(Event event) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int checkDraw = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (buttons[i][j].getText().equals("   ") || buttons[i][j].getText().equals("  ")) {
                    checkDraw++;
                }
            }
        }
        if (checkDraw == 25) {
            winDraw(event, "drawPage.fxml");
        }
        if (checkWin(buttons, "   ") || checkWin(buttons, "  ")) {
            winDraw(event, "winPage.fxml");
        }
    }
    protected void setBackground(Button button,String path) {
        Image image = new Image(Objects.requireNonNull(Application.class.getResource(path)).toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(
                button.getWidth(), button.getHeight(),
                false, false,
                true, true
        );
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize
        );
        Background background = new Background(backgroundImage);
        button.setBackground(background);
    }

    protected void winDraw(Event event, String path) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Util.vsComputer = false;
        MediaPlayer.audio.stop();
        MediaPlayer.changeMusic(Util.MUSIC_PATH_WIN);
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(path));
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Tic Tac Toe");
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public boolean checkWin(Button[][] buttons, String x) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 2; col++) {
                if (buttons[row][col].getText().equals(x) &&
                        buttons[row][col + 1].getText().equals(x) &&
                        buttons[row][col + 2].getText().equals(x) &&
                        buttons[row][col + 3].getText().equals(x)) {
                    return true;
                }
            }
        }

        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 2; row++) {
                if (buttons[row][col].getText().equals(x) &&
                        buttons[row + 1][col].getText().equals(x) &&
                        buttons[row + 2][col].getText().equals(x) &&
                        buttons[row + 3][col].getText().equals(x)) {

                    return true;
                }
            }
        }

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                if (buttons[row][col].getText().equals(x) &&
                        buttons[row + 1][col + 1].getText().equals(x) &&
                        buttons[row + 2][col + 2].getText().equals(x) &&
                        buttons[row + 3][col + 3].getText().equals(x)) {
                    return true;
                }
            }
        }

        for (int row = 0; row < 2; row++) {
            for (int col = 3; col < 5; col++) {
                if (buttons[row][col].getText().equals(x) &&
                        buttons[row + 1][col - 1].getText().equals(x) &&
                        buttons[row + 2][col - 2].getText().equals(x) &&
                        buttons[row + 3][col - 3].getText().equals(x)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void setTurnLabel(int randomNum) {
        if (randomNum == 1 && yourChoose_label.getText().equals("X")) {
            turn2.setText("yourTurn");
            turn1.setText("  ");
        } else if (randomNum == 1 && yourChoose_label.getText().equals("O")) {
            turn1.setText("yourTurn");
            turn2.setText("  ");
        } else if (randomNum == 2 && yourChoose_label.getText().equals("X")) {
            turn1.setText("yourTurn");
            turn2.setText("  ");
        } else if (randomNum == 2 && yourChoose_label.getText().equals("O")) {
            turn2.setText("yourTurn");
            turn1.setText("  ");
        }
    }
}
