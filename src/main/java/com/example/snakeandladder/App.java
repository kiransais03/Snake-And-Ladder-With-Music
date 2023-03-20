package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;


import java.io.File;


public class App extends Application {      //Snake And Ladder Class
    public static final int tileSize=40,width=10,height=10,buttonline=tileSize*height+50,infoline=tileSize*height+30;  //Attributes

    Player player1st,player2nd;     //Attributes

    boolean firstplayerturn=true, gamestart=false;   //Attributes
    private int diceValue;     //Attributes

    static MediaPlayer mediaPlayer;


    private Pane createContent()    //Method
    {
        Pane root = new Pane();
        root.setPrefSize(400,500);

        //100 tiles insertion

        for (int i = 0; i <height ; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().addAll(tile);
//                System.out.println(j*tileSize+","+i*tileSize);
            }
        }

        //Inserting image into the board

        Image img =new Image("D:\\Acciojob\\IntelliJ\\demo\\src\\main\\resources\\com\\example\\demo\\img.png");
        ImageView boardimg = new ImageView();
        boardimg.setFitWidth(width*tileSize);
        boardimg.setFitHeight(height*tileSize);
        boardimg.setImage(img);
        root.getChildren().addAll(boardimg);

        Button startButton=new Button("START");
        startButton.setTranslateX(180);
        startButton.setTranslateY(buttonline);

        Button player1Button=new Button("PLAYER-1");
        player1Button.setTranslateX(10);
        player1Button.setTranslateY(buttonline);

        Button player2Button=new Button("PLAYER-2");
        player2Button.setTranslateX(320);
        player2Button.setTranslateY(buttonline);

        Label dicelabel = new Label("Start The Game");
        dicelabel.setTranslateX(165);
        dicelabel.setTranslateY(infoline);

        Label dicelabelpl1 = new Label("Black Coin");
        dicelabelpl1.setTranslateX(12);
        dicelabelpl1.setTranslateY(infoline);

        Label dicelabelpl2 = new Label("White Coin");
        dicelabelpl2.setTranslateX(320);
        dicelabelpl2.setTranslateY(infoline);

        player1st=new Player("Kiran", Color.BLACK,tileSize/2);
        player2nd=new Player("Sai", Color.WHITE,tileSize/2-5);

        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart)
                {
                    if(firstplayerturn)
                    {
                        diceValue = rollDice();
                        //Dice music
                        mediaPlayer.stop();
                        playHitsound("D:\\Acciojob\\IntelliJ\\demo\\src\\main\\resources\\com\\example\\demo\\dice.mp3");
                        mediaPlayer.play();
                        dicelabel.setText("Dice Number "+diceValue);
                        player1st.movePlayer(diceValue);
                        firstplayerturn=!firstplayerturn;
                        if(player1st.checkWinner())
                        {
                            //End Music
                            mediaPlayer.stop();
                            playHitsound("D:\\Acciojob\\IntelliJ\\demo\\src\\main\\resources\\com\\example\\demo\\level.mp3");
                            mediaPlayer.play();
                            dicelabel.setText("Winner is "+player1st.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstplayerturn=true;
                            gamestart=false;
                        }
                    }
                }

            }
        });

        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart)
                {
                    if(!firstplayerturn)
                    {
                        diceValue = rollDice();
                        //Dice music
                        mediaPlayer.stop();
                        playHitsound("D:\\Acciojob\\IntelliJ\\demo\\src\\main\\resources\\com\\example\\demo\\dice.mp3");
                        mediaPlayer.play();
                        dicelabel.setText("Dice Number "+diceValue);
                        player2nd.movePlayer(diceValue);
                        firstplayerturn=!firstplayerturn;
                        if(player2nd.checkWinner())
                        {
                            //End Music
                            mediaPlayer.stop();
                            playHitsound("D:\\Acciojob\\IntelliJ\\demo\\src\\main\\resources\\com\\example\\demo\\level.mp3");
                            mediaPlayer.play();
                            dicelabel.setText("Winner is "+player2nd.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstplayerturn=true;
                            gamestart=false;
                        }
                    }
                }

            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gamestart=true;
                startButton.setDisable(true);
                mediaPlayer.stop();
                playHitsound("D:\\Acciojob\\IntelliJ\\demo\\src\\main\\resources\\com\\example\\demo\\start1.wav");
                mediaPlayer.play();
                player1st.setstart();
                player2nd.setstart();
            }
        });

        root.getChildren().addAll(startButton,player1Button,player2Button,dicelabel,dicelabelpl1,dicelabelpl2,player1st.getCoin(),player2nd.getCoin());


        return root;
    }

    private int rollDice()
    {
        return (int) (Math.random()*6+1);
    }




    public static void  playHitsound(String path)
    {
//            String path =getClass().getResource(fileName).getPath().toString();
//        String path="D:\\Music\\[iSongs.info] 01 - Bhagavad Geetha - (Ghantasala).mp3";
        System.out.println(path);
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.s;

    }


    @Override
    public void start(Stage stage)  {       //Predefined JAVA FX Method

//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
//        System.out.println(App.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake And Ladder");
        playHitsound("D:\\Acciojob\\IntelliJ\\demo\\src\\main\\resources\\com\\example\\demo\\bgdramatic.mp3");
        mediaPlayer.play();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}