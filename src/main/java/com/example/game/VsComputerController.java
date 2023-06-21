package com.example.game;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class VsComputerController extends OneVsOneController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        randomNum = new Random().nextInt(2) + 1;
        buttons = new Button[5][5];
        initialButton();
        setBackgroundColor();
        setActionForButtons();
    }

    @Override
    protected void setActionForButtons() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                final Button BUTTON = buttons[i][j];
                buttons[i][j].setOnAction(event -> {
                    try {
                        setBackgroundImage(event, BUTTON, buttons);
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
    private void setBackgroundImage(Event event, Button button, Button[][] buttons) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Button opponentButton;
        if (button.getText().equals("   ") || button.getText().equals("  ")) {
            return;
        }
        String path1;
        String path2;
        if (yourChoose_label.getText().equals("X")) {
            button.setText("   ");
            path1 = "image/x3.png";
            opponentButton = makeComputerMove();
            opponentButton.setText("  ");
            path2 = "image/o5.png";

        } else {
            button.setText("  ");
            path1 = "image/o5.png";
            opponentButton = makeComputerMove();
            opponentButton.setText("   ");
            path2 = "image/x3.png";
        }
        setBackground(button, path1);
        setBackground(opponentButton, path2);
        finishCondition(event);
    }
    private Button makeComputerMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(5);
            col = random.nextInt(5);
        } while (isMoveValid(row, col) == null);
        return buttons[row][col];
    }
    private Button isMoveValid(int row, int col) {
        if (buttons[row][col].getText().equals("   ") || buttons[row][col].getText().equals("  ")) {
            return null;
        }
        return buttons[row][col];
    }
}
