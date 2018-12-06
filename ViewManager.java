package ru.AMCP.LDD.View;


import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.AMCP.LDD.Models.MenuButton;

public class ViewManager {

    public static int WIDTH=1280;
    public static int HEIGTH=720;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private boolean isOnGame = false;
    private Stage gameStage;
    private AnimationTimer gameTimer;

    public ViewManager(){
        mainPane=new AnchorPane();
        mainScene=new Scene(mainPane, WIDTH, HEIGTH);
        mainStage=new Stage();
        mainStage.setScene(mainScene);
        createBackground();
        createStartButton();
        createExitManager();
    }

    private void createBackground() {
        Image backgroundImage = new Image("ru/AMCP/LDD/Resourses/Background1.png", WIDTH,HEIGTH,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void createStartButton(){
        MenuButton startButton = new MenuButton("START");
        startButton.setLayoutX(545);
        startButton.setLayoutY(500);
        mainPane.getChildren().add(startButton);
        startButton.setOnAction(event -> {
            if (isOnGame == false) {
                GameViewManager gameManager = new GameViewManager();
                isOnGame = true;
                gameStage = gameManager.createNewGame(mainStage);
                gameStage.setResizable(false);
                gameTimer = gameManager.getGameTimer();
            }
            else{
                gameStage.show();
                mainStage.hide();
                gameTimer.start();
            }
        });
    }

    private void createExitManager(){
        MenuButton exitButton = new MenuButton("EXIT");
        exitButton.setLayoutX(545);
        exitButton.setLayoutY(560);
        mainPane.getChildren().add(exitButton);
        exitButton.setOnAction(event -> {
            if (isOnGame == false ) mainStage.close();
            else{
                gameStage.close();
                mainStage.close();
            }
        });
    }

}
