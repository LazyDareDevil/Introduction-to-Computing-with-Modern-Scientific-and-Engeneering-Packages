package ru.AMCP.LDD.View;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.AMCP.LDD.Models.*;
import ru.AMCP.LDD.Models.MenuButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class PauseViewManager {

    private final static String PORTRAIT = "ru/AMCP/LDD/Resourses/MorganPortrait.png";
    private final static String FONT_PATH="ru/AMCP/LDD/Resourses/font_for_prey.otf";
    private final static String BACKGROUND_IMAGE = "ru/AMCP/LDD/Resourses/Background2.png";

    private AnimationTimer timer;
    private GameViewManager game;
    private AnchorPane pausePane;
    private Scene pauseScene;
    private Stage pauseStage;
    private Stage gameStage;
    private PauseSubScene sceneToHide;

    private AnimationTimer gameTimer;

    private PauseSubScene characterPage;
    private PauseSubScene inventory;

    private GameCharacter player;
    ListView<ImageView> in;
    ObservableList<ImageView> array = FXCollections.observableArrayList();
    private List<Collectables> environment;

    private PauseSubScene neuromods;

    public PauseViewManager() {
        pausePane = new AnchorPane();
        pauseScene = new Scene(pausePane, GameViewManager.GAME_WIDTH, GameViewManager.GAME_HEIGHT);
        pauseStage = new Stage();
        pauseStage.setScene(pauseScene);
        createButtons();
        createKeyListeners(pauseScene);
    }

    private void createKeyListeners(Scene scene){
        scene.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.ESCAPE) {
                game.redrawEnvironment();
                gameTimer.start();
                pauseStage.hide();
                gameStage.show();
            }
        });
    }

    public Stage createNewPause(Stage gameStage, GameCharacter player, List<Collectables> environment, AnimationTimer gameTimer,GameViewManager game){

        this.game = game;
        this.gameStage = gameStage;
        this.player = player;
        this.environment = environment;
        this.gameTimer = gameTimer;
        in = new ListView<ImageView>();
        updateInventory();
        createBackground();
        createSubScene();
        this.gameStage.hide();
        pauseStage.show();
        game.cleanEnvironment();
        return pauseStage;
    }

    private void updateInventory(){
        array.removeAll();
        in.getItems().clear();
        for(int i = 0; i< player.getInventory().size();++i){
            array.add(player.getInventory().get(i).getIcon());
        }
        in.setItems(array);
        return;
    }

    private void createButtons() {
        createCharacterListButton();
        createInventoryButton();
        createNeuromodsButton();
    }

    private void createSubScene() {
        createCharacterPageSubScene();
        createInventorySubScene(this.player.getInventory());
        createNeuromodsSubScene();
    }

    private void createCharacterPageSubScene(){
        characterPage = new PauseSubScene();
        pausePane.getChildren().add(characterPage);

        ImageView portrait = new ImageView(PORTRAIT);
        portrait.setFitWidth(300);
        portrait.setFitHeight(400);
        portrait.setLayoutX(360);
        portrait.setLayoutY(20);
        characterPage.getPane().getChildren().add(portrait);
        //and tasks may be :)
    }

    private void createInventorySubScene(ArrayList<Collectables> elements){
        inventory = new PauseSubScene();
        pausePane.getChildren().add(inventory);
        inventory.getPane().getChildren().add(in);
        in.setOnMouseClicked(event ->{
            MenuItem use = new MenuItem("USE")  ;
            MenuItem abort = new MenuItem("ABORT");

            int index = in.getSelectionModel().getSelectedIndex();
            use.setOnAction(event1 -> {
                if (elements.get(index).getFlag() == 4){
                    player.setArmored(true, ((Weapon) elements.get(index)).getDamage());
                }else {
                    if (elements.get(index).getFlag() == 3){
                        showSubScene(neuromods);
                    }
                    player.getEffect(elements.get(index).getFlag());
                    elements.remove(index);
                }
                updateInventory();
            });
            abort.setOnAction(event1 ->{
                Collectables e = elements.get(index);
                if (e.getFlag() == 4){
                    player.setArmored(false, 0);
                }
                e.setPositionX(player.getPositionX());
                e.setPositionY(player.getPositionY());
                environment.add(e);
                elements.remove(index);
                updateInventory();
            });
            ContextMenu menu = new ContextMenu(use,abort);
            in.setContextMenu(menu);
        });
    }

    private void createNeuromodsSubScene(){
        neuromods = new NeuromodsSubScene(player);
        pausePane.getChildren().add(neuromods);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ((NeuromodsSubScene) neuromods).redrawSubScene();
            }
        };
        timer.start();
    }

    private void createCharacterListButton(){
        MenuButton button = new MenuButton("MORGAN YU");
        button.setLayoutX(300);
        button.setLayoutY(50);
        button.setOnAction(event -> showSubScene(characterPage));
        pausePane.getChildren().add(button);
    }

    private void createInventoryButton(){
        MenuButton button = new MenuButton("INVENTORY");
        button.setLayoutX(540);
        button.setLayoutY(50);
        button.setOnAction(event -> {
            updateInventory();
            showSubScene(inventory);
        });
        pausePane.getChildren().add(button);
    }

    private void createNeuromodsButton(){
        MenuButton button = new MenuButton("NEUROMODS");
        button.setLayoutX(780);
        button.setLayoutY(50);
        button.setOnAction(event -> {
            showSubScene(neuromods);
            timer.start();
        });
        pausePane.getChildren().add(button);
    }

    private void showSubScene(PauseSubScene subScene){
        if(sceneToHide != null){
            sceneToHide.moveSubscene();
            if (sceneToHide == neuromods) ((NeuromodsSubScene) neuromods).redrawSubScene();
        }
        subScene.moveSubscene();
        sceneToHide=subScene;
    }

    private void createBackground() {
        Image backgroundImage = new Image(BACKGROUND_IMAGE
                , GameViewManager.GAME_WIDTH,GameViewManager.GAME_WIDTH,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        pausePane.setBackground(new Background(background));
    }
}
