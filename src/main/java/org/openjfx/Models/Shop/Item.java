package org.openjfx.Models.Shop;

public abstract class Item {

    private final long id;
    private final String name;
    private final long cost;
    private final ItemType type;
    private final long pv;
    private final long resource;

    public Item(String itemName, long cost, ItemType type, long id, long pv, long resource) {
        this.id = id;
        name = itemName;
        this.cost = cost;
        this.type = type;
        this.pv = pv;
        this.resource = resource;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCost() {
        return cost;
    }

    public ItemType getType() {
        return type;
    }

    public long getPv() {
        return pv;
    }

    public long getResource() {
        return resource;
    }
}