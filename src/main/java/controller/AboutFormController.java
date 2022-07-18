package controller;

import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AboutFormController {

    public AnchorPane anchorAbout;


    public ImageView imgID;

    public void initialize(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700), imgID );
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.playFromStart();

        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(500), anchorAbout );
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);
        fadeTransition1.playFromStart();
    }
}
