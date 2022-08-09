package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Background;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.jshell.spi.SPIResolutionException;

import java.awt.datatransfer.Clipboard;
import java.io.*;

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
    public MenuItem mnEditCopy;

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

    public void mnFileOpenOnAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
       // fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("text files(*.txt)", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("dep9 files(*.dep9)","*.dep9"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(null);
        if (file==null) return;

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        StringBuffer stringBuffer = new StringBuffer();

        while (true) {
            byte[] buffer = new byte[1024 * 10];
            int read = bis.read(buffer);
            if (read == -1) return;
            for (int i = 0; i < read; i++) {
                stringBuffer.append((char) (~buffer[i]));
            }
            txtEditor.setHtmlText(stringBuffer.toString());
        }


    }

    public void mnFileSaveOnAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName("depFile.dep9");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("dep9 files(*.dep9)","*.dep9"));
        File file = fileChooser.showSaveDialog(null);
        if (file==null) return;

        if (!file.exists()){
            file.createNewFile();
        }else {
            new Alert(Alert.AlertType.ERROR,"File name already exists..").showAndWait();
            return;
        }

        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);


        String htmlText = txtEditor.getHtmlText();
        char[] chars = htmlText.toCharArray();
        for (char aChar : chars) {
            bos.write(~aChar);
        }

        bos.close();

    }

    public void mnFilePrintOnAction(ActionEvent actionEvent) {
        if (Printer.defaultPrinterProperty()==null){
            new Alert(Alert.AlertType.ERROR,"No default printer available").showAndWait();
        }

        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob !=null){
            printerJob.showPageSetupDialog(txtEditor.getScene().getWindow());
            boolean success = printerJob.printPage(txtEditor);
            if (success){
                printerJob.endJob();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail to complete print").showAndWait();
            }

        }else {
            new Alert(Alert.AlertType.ERROR,"Failed to initialized a printer job!").show();
        }
    }


    public void mnEditCutOnAction(ActionEvent actionEvent) {
    }

    public void mnEditPasteOnAction(ActionEvent actionEvent) {
    }

    public void mnEditSelectAllOnAction(ActionEvent actionEvent) {

    }

    public void mnEditCopyOnAction(ActionEvent actionEvent) {
        ClipboardContent clipboardContent = new ClipboardContent();
        String string = clipboardContent.getString();
        txtEditor.setHtmlText(string);
    }
}
