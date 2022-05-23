package org.openjfx.Models.Shop;

public class Weapon extends Item{
    private long damage;

    public Weapon(long damage, String itemName, long cost){
        super(itemName, cost);
        this.damage = damage;
    }

    public long getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
