package ru.AMCP.LDD.Models;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ActionButton extends Button {
    private final String FONT_PATH = "ru/AMCP/LDD/Resourses/font_for_prey.otf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color:transparent; -fx-background-image:url('ru/AMCP/LDD/Resourses/blue_button03.png')";
    private final String BUTTON_FREE_STYLE = "-fx-background-color:transparent; -fx-background-image:url('ru/AMCP/LDD/Resourses/blue_button02.png')";

    public ActionButton(String text){
        setText(text);
        setButtonFont();
        setPrefWidth(120);
        setPrefHeight(35);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListener();
    }

    private void setButtonFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana",23));
        }
    }

    private void setButtonPressedStyle(){
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY()+4);
    }

    private void setButtonReleasedStyle(){
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(50);
        setLayoutY(getLayoutY()-5);
    }

    private void initializeButtonListener(){

        setOnMousePressed(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                setButtonPressedStyle();
            }
        });

        setOnMouseReleased(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                setButtonReleasedStyle();
            }
        });

        setOnMouseEntered(event -> setEffect(new DropShadow()));
        setOnMouseExited(event -> setEffect(null));
    }

}
