package org.openjfx.Models.Personnage;

public abstract class Ennemie extends Personnage {
    protected int loot;

    public Ennemie() {
    }

    public abstract void loot(Player var1);

    public abstract String criArrivee();

    public abstract String criMort();
}
