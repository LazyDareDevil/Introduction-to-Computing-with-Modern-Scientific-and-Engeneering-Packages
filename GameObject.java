package ru.AMCP.LDD.Models;

import javafx.scene.image.ImageView;
import ru.AMCP.LDD.View.GameViewManager;

import static java.lang.Math.sqrt;

public class GameObject {

    private ImageView img;
    private double positionX;
    private double positionY;
    private boolean inInventory;

    private double object_width;
    private double object_height;
    private double radius = 0;

    public GameObject()
    {
        positionX = GameViewManager.GAME_WIDTH/2;
        positionY = GameViewManager.GAME_HEIGHT/2;
        object_height =0;
        object_width=0;
    }

    public GameObject(String url, double posx, double posy){
        this.setImage(url);
        positionX = posx;
        positionY = posy;
        img.setLayoutX(positionX);
        img.setLayoutY(positionY);
        setObjectSize(40, 40);
    }

    public void setImage(String i)
    {
        img = new ImageView(i);
        setObjectSize(40,40);
        img.setLayoutX(this.positionX);
        img.setLayoutY(this.positionY);
    }

    public void setImage(ImageView i){
        img = i;
        setObjectSize(40,40);
        img.setLayoutX(this.positionX);
        img.setLayoutY(this.positionY);
    }

    public ImageView getImage(){
        return img;
    }

    public void setObjectSize(double a, double b){
        object_width = a;
        object_height = b;
        radius = sqrt((object_width/2)*(object_width/2)+(object_height /2)*(object_height /2));
    }

    public double getObject_width(){
        return object_width;
    }

    public double getObject_height(){
        return object_height;
    }

    public void setPositionX(double x){
        positionX = x;
        img.setLayoutX(positionX);
    }

    public void setPositionY(double y){
        positionY = y;
        img.setLayoutY(positionY);
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getCenterX() { return positionX+object_width/2; }

    public double getCenterY() { return positionY+ object_height /2; }

    public double getRadius() { return radius;}

    public boolean isInInventory() { return inInventory; }

    public void setInInventory() { this.inInventory = true; }

    public void getFromInventory() {this.inInventory = false; }

    public void setObject_width(double a){this.object_width = a;}

    public void setObject_height(double a){this.object_height = a;}

}
