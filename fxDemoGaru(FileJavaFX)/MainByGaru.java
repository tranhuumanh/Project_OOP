package com.example.demogaru;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainByGaru extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainByGaru.class.getResource("tab1.fxml"));
        ControllerByGaru hello = new ControllerByGaru();
        fxmlLoader.setController(hello);
        Scene scene = new Scene(fxmlLoader.load(), 832, 586);
        stage.setTitle("Từ điển Việt Anh");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException{
        launch();
    }
}