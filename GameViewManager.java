package ru.AMCP.LDD.View;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.AMCP.LDD.Models.*;
import ru.AMCP.LDD.Resourses.Enemies;
import ru.AMCP.LDD.Resourses.Level;
import ru.AMCP.LDD.Resourses.Lockers;
import ru.AMCP.LDD.Resourses.Stuff;

import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class GameViewManager {
    public static final int GAME_WIDTH=1280;
    public static final int GAME_HEIGHT=720;
    //
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private Stage pauseStage;
    //
    private Label namehealth;
    private Label nameStamina;
    private ImageView healthBack;
    private ImageView staminaBack;
    private ImageView healthNow;
    private ImageView staminaNow;
    private Label useText;
    private Label openText;
    //
    private AnimationTimer gameTimer;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isFKeyPressed;
    private boolean isPauseOpened;
    private boolean isBoosted;
    private boolean isAttaked;
    //
    private GameCharacter player;
    private List<Wall> walls;
    private List<Collectables> takebale;
    private List<Locker> lockers;
    private List<Enemy1> enemies;
    //
    final Random random = new Random();
    //

    public GameViewManager(){
        initializeStage();
        createKeyListeners();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    private void createKeyListeners() {
        gameScene.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.ESCAPE){
                gameTimer.stop();
                gameStage.hide();
                menuStage.show();
            }else if (event.getCode() == KeyCode.SPACE){
                    gameTimer.stop();
                    PauseViewManager pauseManager = new PauseViewManager();
                    pauseStage = pauseManager.createNewPause(gameStage,player, takebale, gameTimer, this);

            }else if (event.getCode() == KeyCode.LEFT) {
                isLeftKeyPressed=true;
            } else if (event.getCode() == KeyCode.RIGHT) {
                isRightKeyPressed=true;
            } else if (event.getCode() == KeyCode.UP) {
                isUpKeyPressed=true;
            } else if (event.getCode() == KeyCode.DOWN) {
                isDownKeyPressed=true;
            } else if (event.getCode() == KeyCode.SHIFT){
                isBoosted = true;
            } else if (event.getCode() == KeyCode.F){
                isFKeyPressed = true;
            } else if (event.getCode() == KeyCode.K){
                isAttaked = true;
            }
        });

        gameScene.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.LEFT){
                isLeftKeyPressed=false;
            }else if(event.getCode()== KeyCode.RIGHT){
                isRightKeyPressed=false;
            }else if (event.getCode() == KeyCode.UP) {
                isUpKeyPressed=false;
            } else if (event.getCode() == KeyCode.DOWN) {
                isDownKeyPressed=false;
            }else if (event.getCode() == KeyCode.F){
                isFKeyPressed = false;
            }else if (event.getCode() == KeyCode.F){
                isFKeyPressed = false;
            }else if (event.getCode() == KeyCode.SHIFT){
                isBoosted = false;
            }else if (event.getCode() == KeyCode.K){
                isAttaked = false;
            }
        });

    }

    public Stage createNewGame(Stage menuStage){
        this.menuStage = menuStage;
        this.menuStage.hide();

        createBackground();
        createEnvironment();
        createStatistics();
        useText = createLabel("F| TAKE", 0, 0);
        openText = createLabel("F| OPEN", 0, 0);
        createPlayer();
        createGameLoop();
        gameStage.show();
        return gameStage;
    }

    private void createBackground() {
        Image backgroundImage = new Image("ru/AMCP/LDD/Resourses/Game1.png", GAME_WIDTH,GAME_HEIGHT,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(background));
    }

    private void createEnvironment() {
        createWalls();

        takebale = new ArrayList<Collectables>();
        lockers = new ArrayList<Locker>();
        enemies = new ArrayList<Enemy1>();

        for (Stuff elements: Stuff.values()){
            if (elements.getType() == 4){
                Weapon e = new Weapon(elements.getType(),elements.getImg(),elements.getIcon(),elements.getPosX(),elements.getPosY());
                takebale.add(e);
                gamePane.getChildren().add(e.getImage());
            } else {
                Collectables e = new Collectables(elements.getType(), elements.getImg(), elements.getIcon(), elements.getPosX(), elements.getPosY());
                takebale.add(e);
                gamePane.getChildren().add(e.getImage());
            }
        }
        for (Lockers locks: Lockers.values()){
            Locker l = new Locker(locks.getUrlImage(),locks.getPosx(),locks.getPosy(),locks.getNumberOfInsides(),locks.getFlags());
            lockers.add(l);
            gamePane.getChildren().add(l.getImage());
        }
        for (Enemies en: Enemies.values()){
            Enemy1 m = new Enemy1(en.getImage(),en.getHimage(),en.getPositionX(),en.getPositionY());
            enemies.add(m);
            gamePane.getChildren().add(m.getImage());
        }
    }

    public void cleanEnvironment(){
        for(int i=0;i<takebale.size();++i){
            gamePane.getChildren().remove(takebale.get(i).getImage());
        }
        for(int i=0;i<lockers.size();++i){
            gamePane.getChildren().remove(lockers.get(i).getImage());
        }
        gamePane.getChildren().remove(player.getImage());
    }

    public void redrawEnvironment(){
        for(int i=0;i<takebale.size();++i){
            gamePane.getChildren().add(takebale.get(i).getImage());
        }
        for(int i=0;i<lockers.size();++i){
            gamePane.getChildren().add(lockers.get(i).getImage());
        }
        gamePane.getChildren().add(player.getImage());
    }

    private void createWalls(){
        walls = new ArrayList<Wall>();
        for (Level wall:Level.values()){
            walls.add(new Wall(wall.getLeftTopX(),wall.getRightTopX(),wall.getLeftTopY(),wall.getLeftBotY()));
        }
    }

    private Label createLabel(String text, double posx, double posy){
        Label label = new Label(text);
        label.setLayoutX(posx);
        label.setLayoutY(posy);
        gamePane.getChildren().add(label);
        label.setVisible(false);
        return label;
    }

    private void createPlayer() {
        player = new GameCharacter(GAME_WIDTH/2, GAME_HEIGHT/2);
        gamePane.getChildren().add(player.getImage());
    }

    private void createStatistics(){
        String one = "ru/AMCP/LDD/Resourses/blue_button13.png";
        String two = "ru/AMCP/LDD/Resourses/blue_button03.png";
        namehealth = new Label("HEALTH");
        nameStamina = new Label("STAMINA");
        namehealth.setLayoutX(950);
        namehealth.setLayoutY(10);
        nameStamina.setLayoutX(950);
        nameStamina.setLayoutY(70);
        healthBack = new ImageView(one);
        staminaBack = new ImageView(one);
        healthBack.setLayoutX(1000);
        healthBack.setLayoutY(10);
        staminaBack.setLayoutX(1000);
        staminaBack.setLayoutY(70);
        gamePane.getChildren().addAll(namehealth,nameStamina, healthBack,staminaBack);
        healthNow = new ImageView(two);
        staminaNow = new ImageView(two);
        healthNow.setLayoutX(1000);
        healthNow.setLayoutY(10);
        staminaNow.setLayoutX(1000);
        staminaNow.setLayoutY(70);
        gamePane.getChildren().addAll(healthNow,staminaNow);
    }

    private void changeHealthStatistics(int nowHealth){
        healthNow.setFitWidth((double) 190*nowHealth/player.getMaxhealth());
    }

    private void changeStaminaStatistics(int nowStamina){
        staminaNow.setFitWidth((double) 190*nowStamina/player.getMaxStamina());
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveCharacter();
                //moveEnemy();
                checkIfElementsCollide();
            }
        };
        gameTimer.start();
    }

    private void moveCharacter() {
        if (player.getHealth()<=0){
            gameTimer.stop();
            MenuButton lose = new MenuButton("YOU DIED");
            lose.setLayoutX(GAME_WIDTH/2);
            lose.setLayoutY(GAME_HEIGHT/2);
            gamePane.getChildren().add(lose);
            lose.setOnAction(event -> {
                gameStage.close();
                menuStage.close();
            });
        }
        double delta1 = player.getPositionX()-player.getVelocityX();
        double delta2 = player.getPositionX()+player.getVelocityX();
        double delta3 = player.getPositionY()-player.getVelocityY();
        double delta4 = player.getPositionY()+player.getVelocityY();
        double x1=player.getPositionX(), x2 = x1 + player.getObject_width(),
                y1 = player.getPositionY(), y2 = y1 + player.getObject_height();
        if(isUpKeyPressed){
            boolean collide = true;
            for(int i=0;i<walls.size();++i){
                if ( ( (x1>= walls.get(i).getLeftTopX() && x1<=walls.get(i).getRightTopX()) || (x2>= walls.get(i).getLeftTopX() && x2<=walls.get(i).getRightTopX())) && (delta3<=walls.get(i).getLeftBotY() && delta3>=walls.get(i).getLeftTopY() )){
                    collide = false;
                }
            }
            if (delta3 < (GAME_HEIGHT - 80) && delta3 > 0 && collide) {
                player.setPositionY(delta3);
            }
        }
        if (isLeftKeyPressed){
            boolean collide = true;
            for(int i=0;i<walls.size();++i){
                if ( ( (y1>=walls.get(i).getLeftTopY() && y1<=walls.get(i).getLeftBotY()) || (y2>=walls.get(i).getLeftTopY() && y2<=walls.get(i).getLeftBotY()) ) && (delta1<=walls.get(i).getRightTopX() && delta1>=walls.get(i).getLeftTopX())){
                    collide = false;
                }
            }
            if (delta1 < (GAME_WIDTH - 50) && delta1 > 0 && collide) {
                player.setPositionX(delta1);
            }
        }
        if(isRightKeyPressed){
            boolean collide = true;
            for(int i=0;i<walls.size();++i){
                if ( ( (y1>=walls.get(i).getLeftTopY() && y1<=walls.get(i).getLeftBotY()) || (y2 >= walls.get(i).getLeftTopY() && y2 <= walls.get(i).getLeftBotY()) ) && ((delta2 + player.getObject_width())>= walls.get(i).getLeftTopX() && (delta2 + player.getObject_width())<= walls.get(i).getRightTopX())){
                    collide = false;
                }
            }
            if (delta2 < (GAME_WIDTH - 50) && delta2 > 0 && collide) {
                player.setPositionX(delta2);
            }
        }
        if(isDownKeyPressed){
            boolean collide = true;
            for(int i=0;i<walls.size();++i){
                if ( ( (x1>= walls.get(i).getLeftTopX() && x1<=walls.get(i).getRightTopX()) || (x2>= walls.get(i).getLeftTopX() && x2<=walls.get(i).getRightTopX())) && ((delta4 + player.getObject_height())>=walls.get(i).getLeftTopY() && (delta4 + player.getObject_height())<=walls.get(i).getLeftBotY())){
                    collide = false;
                }
            }
            if (delta4 < (GAME_HEIGHT - 80) && delta4 > 0 && collide) {
                player.setPositionY(delta4);
            }
        }

        if (isBoosted && (isDownKeyPressed || isLeftKeyPressed || isRightKeyPressed || isUpKeyPressed)){
            if(player.getStamina()>player.getVelocityStamina()) {
                player.setVelocity(4, 4);
                player.setStamina(player.getStamina() - player.getVelocityStamina());
                changeStaminaStatistics(player.getStamina());
            }
            else {
                player.setVelocity(2,2);
            }
        }
        else {
            player.setVelocity(2,2);
            if (player.getStamina()<player.getMaxStamina()){
                player.setStamina(player.getStamina() + player.getVelocityStamina()/3);
                changeStaminaStatistics(player.getStamina());
            }
        }
    }

    private void moveEnemy(){
        for(int i=0;i<enemies.size();++i){
            Enemy1 mimic = enemies.get(i);
            if (!mimic.isHidden()){
                if ((abs(mimic.getPosInX() - mimic.getToPosX()) <= mimic.getVelocity()) || (abs(mimic.getPosInY() - mimic.getToPosY()) <= mimic.getVelocity())) {
                    double koef = (random.nextBoolean() == true) ? 1. : (-1.);
                    mimic.setToPosX(mimic.getPositionX() + ((double) random.nextInt(mimic.getRadiusVisibility() - 2)) * koef);
                    koef = (random.nextBoolean() == true) ? 1. : (-1.);
                    mimic.setToPosY(mimic.getPositionY() + ((double) random.nextInt(mimic.getRadiusVisibility() - 2)) * koef);
                } else {
                    double koef = 1.;
                    if (mimic.getPosInX() > mimic.getToPosX()) koef = -1.;
                    mimic.setPosInX(mimic.getPosInX() + koef * ((double) mimic.getVelocity()));
                    mimic.setPosInY(mimic.getPosInY() + koef * ((double) mimic.getVelocity()) * (((double) (mimic.getToPosY() - mimic.getPosInY())) / ((double) (mimic.getToPosX() - mimic.getPosInX()))));
                }
            }
        }
    }

    private void checkIfElementsCollide(){

        double centerX = player.getCenterX();
        double centerY = player.getCenterY();
        double radius = player.getRadius();

        for (int i=0;i<enemies.size();++i){
            Enemy1 mimic = enemies.get(i);
            double x = mimic.getPositionX();
            double y = mimic.getPositionY();
            double r = mimic.getRadiusVisibility();
            if (sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)) <= r+radius) {
                if (mimic.isHidden()){
                    gamePane.getChildren().remove(mimic.getImage());
                    mimic.setPosInX(centerX);
                    mimic.setPosInY(centerY);
                    mimic.setHidden(false);
                    gamePane.getChildren().add(mimic.getImage());
                    player.setHealth(player.getHealth() - mimic.getDamage());
                    mimic.setPosInX(mimic.getPositionX());
                    mimic.setPosInY(mimic.getPositionY());
                    mimic.setWaiting(true);
                    mimic.setTimeForWaiting(random.nextInt(6)+ 4);
                }
                double xx=mimic.getPosInX(), yy = mimic.getPosInY(), rr=mimic.getRadius();

                if (sqrt((xx - centerX) * (xx - centerX) + (yy - centerY) * (yy - centerY)) <= rr+radius){

                    if (mimic.getTimeForWaiting()>0){
                        mimic.setTimeForWaiting(mimic.getTimeForWaiting() - 1);
                    }else {
                        mimic.setWaiting(random.nextBoolean());
                        if (mimic.isWaiting()) {
                            mimic.setTimeForWaiting(random.nextInt(7)+5);
                        }
                    }
                    if (isFKeyPressed && mimic.isWaiting()){
                        mimic.setHealth(mimic.getHealth() - player.getDamage());
                        if (mimic.getHealth()<=0) {
                            mimic.setAlive(false);
                            enemies.remove(mimic);
                        }
                        isFKeyPressed = false;
                    }else{
                        double koef = (random.nextBoolean()) ? 1.: (-1.);
                        double a = (double) (random.nextInt(mimic.getRadiusVisibility()-25) + 25)*koef;
                        koef = (random.nextBoolean()) ? 1.: (-1.);
                        double b = (double) random.nextInt(mimic.getRadiusVisibility()-25) + 25;
                        if (a<=radius && b<=radius){
                            mimic.setPosInX(centerX);
                            mimic.setPosInY(centerY);
                            player.setHealth(player.getHealth() - mimic.getDamage());
                        }else{
                            mimic.setPosInX(mimic.getPosInX() + koef * ((double) mimic.getVelocity()));
                            mimic.setPosInY(mimic.getPosInY() + koef * ((double) mimic.getVelocity()) * (((double) (mimic.getToPosY() - mimic.getPosInY())) / ((double) (mimic.getToPosX() - mimic.getPosInX()))));
                        }
                    }
                }
            }
        }
        for(int i = 0; i< takebale.size(); ++i){
            Collectables e = takebale.get(i);
            double x = e.getCenterX();
            double y = e.getCenterY();
            double r = e.getRadius();

            if ( sqrt( (x-centerX)*(x-centerX) + (y-centerY)*(y-centerY)) <= radius + r){
                if (isFKeyPressed) {
                    if (e.getFlag() == 4){
                        gamePane.getChildren().remove(player.getImage());
                        player.setArmored(true,((Weapon) e).getDamage());
                        gamePane.getChildren().add(player.getImage());
                    }
                    player.setElementIntoInventory(e);
                    gamePane.getChildren().remove(e.getImage());
                    takebale.remove(e);
                    isFKeyPressed = false;
                }
            }
        }
        for (int i=0;i<lockers.size();++i){
            Locker e = lockers.get(i);
            double x = e.getCenterX();
            double y = e.getCenterY();
            double r = e.getRadius();

            if ( sqrt( (x-centerX)*(x-centerX) + (y-centerY)*(y-centerY)) <= radius + r){
                if (isFKeyPressed){
                    Locker l = lockers.get(i);
                    player.getInventory().addAll(l.getInsides());
                    l.getInsides().clear();
                }
            }
        }
    }

    public GameCharacter getPlayer(){ return this.player; }

    public AnimationTimer getGameTimer() { return gameTimer;}

    public boolean isPauseOpened() { return isPauseOpened;}
}
