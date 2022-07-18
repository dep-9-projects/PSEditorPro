package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class SplashScreenFormController {

    public Label lblLoad;

    public void initialize(){
        Timeline timeline = new Timeline();

        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoad.setText("Loading Resources...");
            }
        });
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoad.setText("Adding External Libraries.");
            }
        });
        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(3000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoad.setText("Loading plugins..");
            }
        });
        KeyFrame keyFrame4 = new KeyFrame(Duration.millis(4000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoad.setText("Initializing UI...");
            }
        });
        KeyFrame keyFrame5 = new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoad.setText("Getting Start..");
            }
        });
        KeyFrame keyFrame6 = new KeyFrame(Duration.millis(6000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoad.setText("Welcome to PSEditorPro..");
            }
        });
        KeyFrame keyFrame7 = new KeyFrame(Duration.millis(6500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                URL resource = this.getClass().getResource("/view/TextEditorMainForm.fxml");

                try {
                    Parent textEditorContainer = FXMLLoader.load(resource);
                    Scene mainScene = new Scene(textEditorContainer);
                    Stage stage = new Stage();
                    stage.setScene(mainScene);
                    stage.setTitle("PS-Editor-pro");
                    stage.show();
                    stage.centerOnScreen();
                    lblLoad.getScene().getWindow().hide();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5,keyFrame6,keyFrame7);
        timeline.playFromStart();
    }
}
