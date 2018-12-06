package ru.AMCP.LDD.Models;

import javafx.scene.image.ImageView;

public class Collectables extends GameObject{

    private ImageView icon;
    private int flag;
    //private String flag;
    private boolean isCollected;

    public Collectables(int type, String url1, String url2, double posx, double posy){
        super(url1,posx,posy);
        icon = new ImageView(url2);
        flag = type;
        this.setObjectSize(40,40);
    }

    public void setIcon(String i)
    {
        icon = new ImageView(i);
    }

    public ImageView getIcon() { return icon;}

    public int getFlag() { return flag; }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }
}
