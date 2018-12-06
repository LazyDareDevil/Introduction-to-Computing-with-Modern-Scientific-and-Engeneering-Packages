package ru.AMCP.LDD.Models;

public class Wall {

    private double leftTopX,rightTopX,leftTopY,leftBotY;

    public Wall(double x1, double x2, double y1, double y2){
        leftTopX=x1; leftTopY=y1;
        rightTopX = x2; leftBotY = y2;
    }

    public double getLeftTopX() {
        return leftTopX;
    }

    public double getRightTopX() {
        return rightTopX;
    }

    public double getLeftTopY() {
        return leftTopY;
    }

    public double getLeftBotY() {
        return leftBotY;
    }

}
