package com.example.snakeandladder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javafx.scene.media.Media;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    MediaPlayer mediaPlayer;

        private void  playHitsound()
        {
//            String path =getClass().getResource(fileName).getPath().toString();
            String path="D:\\Music\\[iSongs.info] 01 - Bhagavad Geetha - (Ghantasala).mp3";
            System.out.println(path);
            Media media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
//        playHitsound();
//        mediaPlayer.play();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}