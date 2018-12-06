package ru.AMCP.LDD.Models;

import java.util.ArrayList;

public class GameCharacter extends GameObject {

    public static final String MORGAN = "ru/AMCP/LDD/Resourses/Morgan.png";
    public static final String ARMORED = "ru/AMCP/LDD/Resourses/MorganArmored.png";

    private int maxhealth = 200;
    private int maxStamina = 2000;
    private int health = 200;
    private int stamina = 2000;
    private int damage = 0;
    private int selfDamage = 0;
    private double velocityX;
    private double velocityY;
    private int velocityStamina = 30;
    private ArrayList<Collectables> inventory;
    private boolean isArmored = false;

    public GameCharacter(double posx, double posy){
        super();
        velocityX = 2;
        velocityY = 2;
        this.setImage(MORGAN);
        this.setPositionX(posx);
        this.setPositionY(posy);
        this.setObjectSize(50,80);
        inventory = new ArrayList<Collectables>();
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public double getVelocityX(){
        return velocityX;
    }

    public double getVelocityY(){
        return velocityY;
    }

    public void addVelocity(double x, double y)
    {
        velocityX+=x;
        velocityY+=y;
    }

    public void setElementIntoInventory(Collectables elem){
        inventory.add(elem);
    }

    public void getElementFromInventory(Collectables elem){
        int pos = inventory.indexOf(elem);
        if (pos >=0) inventory.remove(pos);
    }

    public Collectables getElementFromInventory(int i){
        Collectables e = inventory.get(i);
        inventory.remove(i);
        return e;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) { this.health = health; }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getVelocityStamina() {
        return velocityStamina;
    }

    public void setVelocityStamina(int velocityStamina) {
        this.velocityStamina = velocityStamina;
    }

    public void getEffect(int flag){
        switch(flag){
            case 1:{
                this.health += 50;
                break;
            }
            case 2:{
                this.stamina += 1000;
                break;
            }
            case 4:{
                this.damage = 15;
                this.setImage(ARMORED);
                break;
            }
        }
    }

    public int getDamage() {
        return damage + selfDamage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ArrayList<Collectables> getInventory(){ return inventory; }

    public int getMaxhealth() {
        return maxhealth;
    }

    public void setMaxhealth(int maxhealth) {
        this.maxhealth = maxhealth;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public boolean isArmored() {
        return isArmored;
    }

    public void setArmored(boolean armored, int damage) {
        if (armored){
            this.setImage(ARMORED);
            this.setObjectSize(50,80);
            this.getImage().setLayoutX(this.getPositionX());
            this.getImage().setLayoutY(this.getPositionY());
            isArmored = armored;
            this.setDamage(damage);
        }
        else{
            isArmored=armored;
            this.setImage(MORGAN);
            this.setObjectSize(50,80);
            this.getImage().setLayoutX(this.getPositionX());
            this.getImage().setLayoutY(this.getPositionY());
            this.setDamage(damage);
        }
    }

    public int getSelfDamage() {
        return selfDamage;
    }

    public void setSelfDamage(int selfDamage) {
        this.selfDamage = selfDamage;
    }
}
