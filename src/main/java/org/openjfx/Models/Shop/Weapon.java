package org.openjfx.Models.Shop;

public class Weapon extends Item {
    private final long damage;
    private final long critique;

    public Weapon(long damage, String itemName, long cost, long id, long pv, long critique, long resource) {
        super(itemName, cost, ItemType.Weapon, id, pv, resource);
        this.damage = damage;
        this.critique = critique;
    }

    public long getDamage() {
        return damage;
    }

    public long getCritique() {
        return critique;
    }
}
