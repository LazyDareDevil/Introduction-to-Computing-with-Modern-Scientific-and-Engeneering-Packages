package ru.AMCP.LDD.Resourses;

public enum Stuff {

    FIRSTAIDKIT1(1,"ru/AMCP/LDD/Resourses/FirstAidKit.png","ru/AMCP/LDD/Resourses/FirstAidKit.png",23.,256.),
    INJECTOR1(2,"ru/AMCP/LDD/Resourses/Injector.png","ru/AMCP/LDD/Resourses/Injector.png",245.,670.),
    NEUROMOD1(3, "ru/AMCP/LDD/Resourses/Neuromod.png","ru/AMCP/LDD/Resourses/Neuromod.png",567., 1000.),
    WRENCH1(4, "ru/AMCP/LDD/Resourses/Weapon.png","ru/AMCP/LDD/Resourses/Weapon.png", 110., 110.);

    private int type;
    private String img;
    private String icon;
    private double positionX;
    private double positionY;

    private Stuff(int type, String url1, String url2, double pox, double posy){
        this.type = type;
        this.img = url1;
        this.icon = url2;
        this.positionX = pox;
        this.positionY = posy;
    }

    public String getImg() { return img;}

    public String getIcon() { return icon; }

    public double getPosX() {
        return positionX;
    }

    public double getPosY() {
        return positionY;
    }

    public int getType(){ return type;}
}
