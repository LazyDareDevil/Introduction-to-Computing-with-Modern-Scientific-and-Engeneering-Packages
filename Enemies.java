package ru.AMCP.LDD.Resourses;

public enum Enemies {

    MIMIC1("ru/AMCP/LDD/Resourses/Mimic.png", "ru/AMCP/LDD/Resourses/Neuromod.png",1000, 500);

    private String image;
    private String himage;
    private double positionX;
    private double positionY;

    private Enemies(String url, String himage, double posX, double posY){
        this.image = url;
        this.himage = himage;
        this.positionX = posX;
        this.positionY = posY;

    }

    public String getImage() {
        return image;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public String getHimage() {
        return himage;
    }
}
