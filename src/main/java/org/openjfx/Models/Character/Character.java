package org.openjfx.Models.Character;

public abstract class Character {
    private int health;
    private int shield;
    private int damage;
    private String name;
    private double dodge;
    private int maxHealth;

    public Character(String name, int health, int shield, int damage, double dodge) {
        this.health = health;
        this.maxHealth = health;
        this.shield = shield;
        this.damage = damage;
        this.name = name;
        this.dodge = dodge;
    }

    public boolean isAlive() {
        if (health > 0) return true;
        else return false;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0) this.health = 0;
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

    public void attack(Character character) {
        if (character.esquive()) return;

        int damageToDo = character.armure(this.getDamage());
        character.setHealth(character.getHealth() - damageToDo);
    }

    public boolean esquive() {
        int roll = (int) (Math.random() * 100);
        if (roll <= dodge) {
            return true;
        } else {
            return false;
        }
    }

    public int armure(int degat) {
        float reduction = (float) shield / 100;
        int deg = (int) (degat * (1 - reduction));
        return deg;
    }
}
