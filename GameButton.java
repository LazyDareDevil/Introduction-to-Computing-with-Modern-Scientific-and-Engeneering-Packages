package ru.AMCP.LDD.Models;

import javafx.scene.control.TextField;

public class GameButton extends TextField {
    private final String FONT_PATH = "ru/AMCP/LDD/Resourses/font_for_prey.otf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color:transparent; -fx-background-image:url('ru/AMCP/LDD/Resourses/blue_button03.png')";
    private final String BUTTON_FREE_STYLE = "-fx-background-color:transparent; -fx-background-image:url('ru/AMCP/LDD/Resourses/blue_button02.png')";

    public GameButton(String text){
        setText(text);
        setPrefSize(60,20);
        setStyle(BUTTON_FREE_STYLE);
    }
}
