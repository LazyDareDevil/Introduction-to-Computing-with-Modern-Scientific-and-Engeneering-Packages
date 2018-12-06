package ru.AMCP.LDD.Resourses;

public enum Level {

    WALL1(100,150,100, 700);

    private double leftTopX,rightTopX, leftTopY,leftBotY;

    private Level(double x1, double x2, double y1, double y2){
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
