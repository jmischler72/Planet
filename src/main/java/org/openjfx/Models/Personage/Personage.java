package org.openjfx.Models.Personage;

public abstract class Personage {
    private int vie;
    private String nom;

    public Personage(int vie, String nom){
        this.vie = vie;
        this.nom = nom;
    }
}
