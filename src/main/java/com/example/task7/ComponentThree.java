package com.example.task7;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ComponentThree implements IObserver {
    private Timeline colorAnimation;
    private TranslateTransition moveAnimationOne;
    private TranslateTransition moveAnimationTwo;
    private Circle circleOne;
    private Circle circleTwo;
    private Label animationStatusLabel;

    public ComponentThree(Circle circleOne, Circle circleTwo, Label animationStatusLabel) {
        this.circleOne = circleOne;
        this.circleTwo = circleTwo;
        this.animationStatusLabel = animationStatusLabel;
        createAnimations();
    }

    private void createAnimations() {
        colorAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> {
                    circleOne.setFill(Color.BLUE);
                    circleTwo.setFill(Color.GREEN);
                }),
                new KeyFrame(Duration.seconds(1), e -> {
                    circleOne.setFill(Color.RED);
                    circleTwo.setFill(Color.ORANGE);
                }),
                new KeyFrame(Duration.seconds(10), e -> {
                    circleOne.setFill(Color.BLUE);
                    circleTwo.setFill(Color.GREEN);
                })
        );
        colorAnimation.setCycleCount(Timeline.INDEFINITE);

        moveAnimationOne = new TranslateTransition(Duration.seconds(10), circleOne);
        moveAnimationOne.setFromX(0);
        moveAnimationOne.setToX(250);
        moveAnimationOne.setCycleCount(Timeline.INDEFINITE);
        moveAnimationOne.setAutoReverse(true);

        moveAnimationTwo = new TranslateTransition(Duration.seconds(10), circleTwo);
        moveAnimationTwo.setFromX(0);
        moveAnimationTwo.setToX(250);
        moveAnimationTwo.setCycleCount(Timeline.INDEFINITE);
        moveAnimationTwo.setAutoReverse(true);
    }

    @Override
    public void update(Subject subject) {
        TimeServer timeServer = (TimeServer) subject;

        if (timeServer.getState() % 10 == 0) {
            String message = "Анимация перезапущена в " + timeServer.getState() + " секунд";

            Platform.runLater(() -> {
                animationStatusLabel.setText(message);
            });

            if (colorAnimation.getStatus() != Timeline.Status.RUNNING) {
                colorAnimation.play();
            }

            if (moveAnimationOne.getStatus() != Timeline.Status.RUNNING) {
                moveAnimationOne.play();
            }

            if (moveAnimationTwo.getStatus() != Timeline.Status.RUNNING) {
                moveAnimationTwo.play();
            }
        }
    }

    public void stopAnimation() {
        if (colorAnimation.getStatus() == Timeline.Status.RUNNING) {
            colorAnimation.stop();
        }
        if (moveAnimationOne.getStatus() == Timeline.Status.RUNNING) {
            moveAnimationOne.stop();
            Platform.runLater(() -> {
                circleOne.setTranslateX(0);
                animationStatusLabel.setText("Анимация остановлена");
            });
        }
        if (moveAnimationTwo.getStatus() == Timeline.Status.RUNNING) {
            moveAnimationTwo.stop();
            Platform.runLater(() -> {
                circleTwo.setTranslateX(0);
            });
        }
    }
}
