package com.example.task7;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class Controller {
    @FXML
    private Label timeLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label animationStatusLabel;

    @FXML
    private Circle circleOne;
    @FXML
    private Circle circleTwo;

    private TimeServer timeServer;
    private ComponentOne componentOne;
    private ComponentTwo componentTwo;
    private ComponentThree componentThree;

    @FXML
    public void initialize() {
        timeServer = new TimeServer();
        componentOne = new ComponentOne(timeLabel);
        componentTwo = new ComponentTwo();
        componentThree = new ComponentThree(circleOne, circleTwo, animationStatusLabel);

        timeServer.attach(componentOne);
        timeServer.attach(componentTwo);
        timeServer.attach(componentThree);

        startButton.setOnAction(e -> {
            statusLabel.setText("Статус: Активен");
            timeServer.start();
        });

        stopButton.setOnAction(e -> {
            statusLabel.setText("Статус: Неактивен");
            timeServer.stop();
            componentTwo.stopMusic();
            componentThree.stopAnimation();
        });
    }
}
