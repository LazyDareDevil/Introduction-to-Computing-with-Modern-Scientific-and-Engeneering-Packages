package ru.AMCP.LDD.Models;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.EventListener;

public class NeuromodsSubScene extends PauseSubScene {
    private Label num, label;
    private ImageView first, second, third;
    private GameCharacter player;
    private boolean firstIsUsed, secondIsUsed,thirdIsUsed;

    public NeuromodsSubScene(GameCharacter p){
        super();
        player = p;
        num = new Label();
        first = new ImageView("ru/AMCP/LDD/Resourses/blue_button06.png");
        label = new Label();
        second = new ImageView("ru/AMCP/LDD/Resourses/blue_button06.png");
        third = new ImageView("ru/AMCP/LDD/Resourses/blue_button06.png");
        firstIsUsed=false;
        secondIsUsed=false;
        thirdIsUsed=false;
        num.setLayoutX(50);
        num.setLayoutY(370);
        first.setLayoutY(50);
        second.setLayoutY(50);
        third.setLayoutY(50);
        label.setLayoutY(150);
        first.setLayoutX(133);
        second.setLayoutX(315);
        third.setLayoutX(517);
        label.setLayoutX(133);
        this.getPane().getChildren().addAll(first,second,third,label,num);
        num.setVisible(true);
        eventListeners();
    }

    private void eventListeners(){
        first.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!firstIsUsed) {
                    if (event.getClickCount() == 1) {
                        label.setText("Encrease maximum health");
                        label.setVisible(true);
                    } else {
                        label.setVisible(false);
                        int index = 0;
                        while (index < player.getInventory().size() && player.getInventory().get(index).getFlag() != 3) {
                            ++index;
                        }
                        if (index < player.getInventory().size()) {
                            firstIsUsed = true;
                            player.getInventory().remove(index);
                            player.setMaxhealth(player.getMaxhealth() + 50);
                            player.setHealth(player.getMaxhealth());
                        }
                    }
                }
            }
        });
        second.setOnMouseClicked(event->{
            if (!secondIsUsed) {
                if (event.getClickCount() == 1) {
                    label.setText("Encrease maximum stamina");
                    label.setVisible(true);
                }
                if (event.getClickCount() > 1){
                    label.setVisible(false);
                    int index = 0;
                    while (index < player.getInventory().size() && player.getInventory().get(index).getFlag() != 3) {
                        ++index;
                    }
                    if (index < player.getInventory().size()) {
                        secondIsUsed=true;
                        player.getInventory().remove(index);
                        player.setMaxStamina(player.getMaxStamina() + 500);
                        player.setStamina(player.getMaxStamina());
                    }
                }
            }
        });
        third.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!thirdIsUsed) {
                    if (event.getClickCount() == 1) {
                        label.setText("Encrease damage");
                        label.setVisible(true);
                    } else {
                        label.setVisible(false);
                        int index = 0;
                        while (index < player.getInventory().size() && player.getInventory().get(index).getFlag() != 3) {
                            ++index;
                        }
                        if (index < player.getInventory().size()) {
                            thirdIsUsed = true;
                            player.getInventory().remove(index);
                            player.setSelfDamage(player.getSelfDamage() + 15);
                        }
                    }
                }
            }
        });
    }

    public void redrawSubScene(){
        int numberOfneuromods=0;
        for (int i=0;i<player.getInventory().size();++i){
            if (player.getInventory().get(i).getFlag() == 3) ++numberOfneuromods;
        }
        num.setText("NUMBER OF NEUROMODS: "+numberOfneuromods);
        if (firstIsUsed && first!= null) {
            this.getPane().getChildren().remove(first);
            first = null;
        }
        if (secondIsUsed && second!= null) {
            this.getPane().getChildren().remove(second);
            second = null;
        }
        if (thirdIsUsed && third!= null) {
            this.getPane().getChildren().remove(third);
            third = null;
        }
    }
}
