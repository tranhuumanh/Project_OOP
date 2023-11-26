package com.example.demogaru;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;

public class ControllerByGaru {

    @FXML private MenuItem dong;
    @FXML private TextField check;
    @FXML private TextField kq;
    @FXML private Text displayH;

    @FXML
    void closeApp () {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Check");
        alert.setHeaderText("Bạn có muốn đóng Dictionary");
        alert.setContentText("Hãy lựa chọn YES OR NO");

        ButtonType button_yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType button_no = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(button_yes, button_no);

        Optional<ButtonType> result = alert.showAndWait();

        String noti;
        if (result.isPresent()) {
            noti = result.get().getText();
        } else {
            noti = "Cancel";
        }

        if ("Yes".equals(noti)) {
            Platform.exit();
        }
    }

    @FXML
    void LookUp() {
        if (check != null) { // Hàm tìm kiếm từ nghĩa Tiếng Anh
            String content = check.getText();
            kq.setText(DictionaryCommandLine.LookUpCommandLine(content));
        }
    }

    @FXML
    void Reload() {
        kq.setText("");
        check.setText("");
    }

    @FXML
    void History(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tabHistory.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
        StringBuilder content = new StringBuilder();
        //displayH.setText(DictionaryCommandLine.HistoryCommandLine());
    }

    @FXML
    void addF() { //Controller để thêm từ


    }

    @FXML
    void Sounds() {
        if (check != null) { //
            String content = check.getText();
            Sound.Vocal(content);
        }
    }








}