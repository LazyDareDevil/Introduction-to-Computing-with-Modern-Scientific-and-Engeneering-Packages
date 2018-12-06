package ru.AMCP.LDD.Models;

public class Weapon extends Collectables {

    private int damage = 15;

    public Weapon(int type, String url1, String url2, double posx,double posy){
        super(type,url1,url2,posx,posy);
    }

    public int getDamage() {
        return damage;
    }
}
