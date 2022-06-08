package org.openjfx.Models.Character;

import org.openjfx.Models.Character.Enemy.Enemy;
import org.openjfx.Models.Shop.Armor;
import org.openjfx.Models.Shop.Item;
import org.openjfx.Models.Shop.ItemType;
import org.openjfx.Models.Shop.Weapon;

import java.util.ArrayList;

public class Player extends Character {
    private int gold;
    private final ArrayList<Item> items;

    //Stats
    private double critique;
    private int ressource;

    public Player(String nom) {
        super(nom, 100, 10, 10, 0);
        this.critique = 0;
        this.ressource = 100;
        this.gold = 0;
        items = new ArrayList<>();
    }

    public Boolean buy(Item item) { //PlayerController
        if (item.getCost() > this.gold) return false;
        this.gold -= item.getCost();
        this.items.add(item);

        this.setHealth(this.getHealth() + (int) item.getPv());
        this.ressource += (int) item.getResource();

        if (item.getType() == ItemType.Weapon) {
            this.setDamage(getDamage() + (int) ((Weapon) item).getDamage());
            this.critique += (int) ((Weapon) item).getCritique();
        } else {
            this.setShield(getShield() + (int) ((Armor) item).getArmor());
            this.setDodge(getDodge() + (int) ((Armor) item).getEsquive());
        }
        return true;
    }

    public void addGolds(int amount) {
        gold += amount;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getGold() {
        return gold;
    }

    public int getRessource() {
        return ressource;
    }

    public double getCritique() {
        return critique;
    }

    public void attack(Enemy enemy){
        enemy.setHealth(enemy.getHealth()-this.getDamage());
        System.out.println(enemy.getHealth());
        System.out.println(this.getDamage());

    }
}
