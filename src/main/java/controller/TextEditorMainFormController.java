package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TextEditorMainFormController {
    public MenuItem mnFileNew;
    public MenuItem mnFileOpen;
    public MenuItem mnFileSave;
    public MenuItem mnFilePrint;
    public MenuItem mnFileClose;
    public MenuItem mnEditUndo;
    public MenuItem mnEditRedo;
    public MenuItem mnEditCut;
    public MenuItem mnEditPaste;
    public MenuItem mnEditSelectAll;
    public MenuItem mnEditDelete;
    public MenuItem mnHelpAbout;
    public HTMLEditor txtEditor;

    public void initialize(){
        mnHelpAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/AboutForm.fxml"))));
                    stage.setTitle("About");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                    stage.centerOnScreen();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        mnFileNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                txtEditor.setHtmlText("");
            }
        });
        mnFileClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
    }
}
