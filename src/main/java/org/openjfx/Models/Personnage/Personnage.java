package org.openjfx.Models.Personnage;

public class Personnage {
    protected int vie;
    protected int degat;
    protected String nom;
    protected int esquive;
    protected int armure;

    public Personnage() {
    }

    public int getVie() {
        return this.vie;
    }

    public int getDegat() {
        return this.degat;
    }

    public boolean isAlive() {
        return this.vie > 0;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public String getNom() {
        return this.nom;
    }

    public boolean esquive() {
        int roll = (int)(Math.random() * 100.0D);
        if (roll <= this.esquive) {
            return true;
        } else {
            return false;
        }
    }

    public int armure(int degat) {
        int reduction = this.armure / 100;
        return degat * (1 - reduction);
    }
}
