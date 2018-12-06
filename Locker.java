package ru.AMCP.LDD.Models;

import java.util.ArrayList;
import java.util.List;

public class Locker extends GameObject {

    private Integer number_of_insides;
    private ArrayList<Collectables> insides;

    public Locker(String url, double posx, double posy, Integer number_of_insides, int[] flags){
        super(url,posx,posy);
        this.insides = new ArrayList<Collectables>();
        this.number_of_insides = number_of_insides;

        for(int i=0;i<number_of_insides;++i){
            String url1="",url2="";
            int flag = flags[i];
            switch (flag){
                case 1:{
                    url1 = "ru/AMCP/LDD/Resourses/FirstAidKit.png";
                    url2 = "ru/AMCP/LDD/Resourses/FirstAidKit.png";
                    break;
                }
                case 2:{
                    url1 = "ru/AMCP/LDD/Resourses/Injector.png";
                    url2 = "ru/AMCP/LDD/Resourses/Injector.png";
                    break;
                }
                case 3:{
                    url1 = "ru/AMCP/LDD/Resourses/Neuromod.png";
                    url2 = "ru/AMCP/LDD/Resourses/Neuromod.png";
                    break;
                }
                case 4:{
                    url1 = "ru/AMCP/LDD/Resourses/Weapon.png";
                    url2 = "ru/AMCP/LDD/Resourses/Weapon.png";
                    insides.add(new Weapon(flag,url1,url2,posx,posy));
                    continue;
                }
            }
            insides.add(new Collectables(flag,url1,url2,posx,posy));
        }
        this.setObjectSize(40,40);
    }

    public Collectables getElement(int index){
        return this.insides.get(index);
    }

    public ArrayList<Collectables> getInsides(){ return insides; }

    public Integer getNumber_of_insides() { return number_of_insides; }
}
