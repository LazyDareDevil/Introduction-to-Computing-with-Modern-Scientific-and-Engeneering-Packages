package ru.AMCP.LDD.Resourses;

import java.util.ArrayList;
import java.lang.String;

public enum Lockers {

    LOCKER1("ru/AMCP/LDD/Resourses/Locker1.png", 1, 400., 0., "1"),
    LOCKER2("ru/AMCP/LDD/Resourses/Locker2.png", 2, 700., 500., "1,2"),
    LOCKER3("ru/AMCP/LDD/Resourses/Locker1.png", 1, 0., 0., "2");

    private String urlImage;
    private int numberOfInsides;
    private double posx;
    private double posy;
    private String flags;

    Lockers(String urlImage,int numberOfInsides,double pox, double posy, String flags){
        this.urlImage = urlImage;
        this.numberOfInsides = numberOfInsides;
        this.flags = flags;
        this.posx = pox;
        this.posy = posy;
    }

    public double getPosx() {
        return posx;
    }

    public double getPosy() {
        return posy;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public int getNumberOfInsides() {
        return numberOfInsides;
    }

    public int[] getFlags(){
        int[] f = new int[numberOfInsides];
        int poss=0,pose=0;
        for (int i=0;i<numberOfInsides;++i){
            while (pose < flags.length() && flags.charAt(pose) != ','){
                ++pose;
            }
            f[i]=Integer.parseInt(flags.substring(poss,pose));
            ++pose;
            poss=pose;
        }
        return f;
    }
}
