package org.openjfx.Models.Shop;

public class Weapon extends Item{
    private long damage;

    public Weapon(long damage, String itemName, long cost, long id){
        super(itemName, cost, ItemType.Weapon, id);
        this.damage = damage;
    }

    public long getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
