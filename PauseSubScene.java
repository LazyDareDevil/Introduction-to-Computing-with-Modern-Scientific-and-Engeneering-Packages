package ru.AMCP.LDD.Models;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import ru.AMCP.LDD.View.GameViewManager;

public class PauseSubScene extends SubScene {

    private final static String FONT_PATH="ru/AMCP/LDD/Resourses/font_for_prey.otf";
    private final static String BACKGROUND_IMAGE = "ru/AMCP/LDD/Resourses/Background3.png";
    private boolean isHidden=true;

    public PauseSubScene(){
        super(new AnchorPane(), 680, 420);
        prefHeight(680);
        prefWidth(420);
        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,600,400,false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        AnchorPane root2 = (AnchorPane)this.getRoot();
        root2.setBackground(new Background(image));

        setLayoutX(300);
        setLayoutY(GameViewManager.GAME_HEIGHT+10);
    }

    public void moveSubscene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if(isHidden) {
            transition.setToY(-GameViewManager.GAME_HEIGHT+140);
            isHidden=false;
        }else{
            transition.setToY(0);
            isHidden=true;
        }
        transition.play();
    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
