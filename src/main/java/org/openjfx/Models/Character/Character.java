package org.openjfx.Models.Character;

public abstract class Personage {
    private int vie;
    private int armure;
    private int degat;
    private String nom;
    private double esquive;

    public Personage(int vie, int armure, int degat, String nom, double esquive) {
        this.vie = vie;
        this.armure = armure;
        this.degat = degat;
        this.nom = nom;
        this.esquive = esquive;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public String getNom() {
        return nom;
    }

    public double getEsquive() {
        return esquive;
    }

    public void setEsquive(double esquive) {
        this.esquive = esquive;
    }

    public int getArmure() {
        return armure;
    }

    public int getDegat() {
        return degat;
    }

    public void setArmure(int armure) {
        this.armure = armure;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }
}
