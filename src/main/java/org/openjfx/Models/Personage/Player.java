package org.openjfx.Models.Personage;

import org.openjfx.Models.Shop.Item;
import org.openjfx.ViewElements.LevelSelector.ButtonSelector;

import java.util.ArrayList;

public class Player extends Personage{
    private int gold;
    private ArrayList<Item> items;

    public Player(String nom){
        super(100, nom);
        this.gold = 0;
        items = new ArrayList<>();
    }

    public Boolean buy(Item item) {
        if(item.getCost() > this.gold) return false;
        this.gold -= item.getCost();
        this.items.add(item);
        // Set les nouvelles stats du perso
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
}
