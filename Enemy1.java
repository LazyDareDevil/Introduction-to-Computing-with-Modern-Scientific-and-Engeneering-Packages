package ru.AMCP.LDD.Models;

import javafx.scene.image.ImageView;

import java.util.Random;

public class Enemy1 extends GameObject {
    //position - центр окружности видимости
    //posIn - положение врага непосредственно
    //posTo - точка, в которую должен переместиться мимик

    private int radiusVisibility = 100;
    private double posInX, posInY;
    private double toPosX, toPosY;
    private int health = 30;
    private int damage = 40;
    private int velocity = 2;
    private boolean isAlive;
    private boolean isHidden;
    private ImageView hiddenImage;
    private ImageView realImage;

    private boolean isWaiting;
    private int timeForWaiting;

    public Enemy1(){
        super();
        posInX = this.getPositionX();
        posInY = this.getPositionY();
        toPosX = this.getPositionX();
        toPosY = this.getPositionY();
        isWaiting = false;
        timeForWaiting = 0;
        isAlive = true;
        isHidden = true;
    }

    public Enemy1(String url1, String url2, double posx, double posy){
        super(url2,posx,posy);
        posInX = this.getPositionX();
        posInY = this.getPositionY();
        toPosX = this.getPositionX();
        toPosY = this.getPositionY();
        realImage = new ImageView(url1);
        isWaiting = false;
        timeForWaiting = 0;
        isAlive = true;
        isHidden = true;
    }

    public double getPosInX() {
        return posInX;
    }

    public void setPosInX(double posInX) {
        this.posInX = posInX;
        this.getImage().setLayoutX(posInX);
    }

    public double getPosInY() { return posInY;}

    public void setPosInY(double posInY) {
        this.posInY = posInY;
        this.getImage().setLayoutY(posInY);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public double getToPosX() {
        return toPosX;
    }

    public void setToPosX(double toPosX) {
        this.toPosX = toPosX;
    }

    public double getToPosY() {
        return toPosY;
    }

    public void setToPosY(double toPosY) {
        this.toPosY = toPosY;
    }

    public int getRadiusVisibility() {
        return radiusVisibility;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity){
        this.velocity = velocity;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }

    public int getTimeForWaiting() {
        return timeForWaiting;
    }

    public void setTimeForWaiting(int timeForWaiting) {
        this.timeForWaiting = timeForWaiting;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public ImageView getHiddenImage() {
        return hiddenImage;
    }

    public void setHiddenImage(String hiddenImage) {
        this.hiddenImage = new ImageView(hiddenImage);
        this.hiddenImage.setLayoutX(this.getPositionX());
        this.hiddenImage.setLayoutY(this.getPositionY());
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
        if (hidden){
            this.setImage(hiddenImage);
        }
        else{
            this.setImage(realImage);
        }
        this.getImage().setLayoutX(posInX);
        this.getImage().setLayoutY(posInY);
    }
}
