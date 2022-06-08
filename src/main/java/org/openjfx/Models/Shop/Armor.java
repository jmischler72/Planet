package org.openjfx.Models.Shop;

public class Armor extends Item {
    private final long armor;
    private final long esquive;

    public Armor(String itemName, long cost, long id, long pv, long armor, long esquive, long resource) {
        super(itemName, cost, ItemType.Armor, id, pv, resource);
        this.armor = armor;
        this.esquive = esquive;
    }

    public long getArmor() {
        return armor;
    }

    public long getEsquive() {
        return esquive;
    }
}
