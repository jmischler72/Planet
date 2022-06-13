package org.openjfx.Models.Character;

import org.openjfx.Models.Character.Enemy.Enemy;

public abstract class Character {
    private int health;
    private int shield;
    private int damage;
    private String name;
    private double dodge;
    private int maxHealth;


    public Character(String name, int health, int shield, int damage , double dodge) {
        this.health = health;
        this.maxHealth = health;
        this.shield = shield;
        this.damage = damage;
        this.name = name;
        this.dodge = dodge;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public double getDodge() {
        return dodge;
    }

    public void setDodge(double dodge) {
        this.dodge = dodge;
    }

    public int getShield() {
        return shield;
    }
    public void setShield(int shield) {
        this.shield = shield;
    }


    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void attack(Character character){
        character.setHealth(character.getHealth()-this.getDamage());
//        System.out.println(enemy.getHealth());
//        System.out.println(this.getDamage());

    }
}
