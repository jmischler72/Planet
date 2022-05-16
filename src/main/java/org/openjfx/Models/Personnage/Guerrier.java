package org.openjfx.Models.Personnage;

public class Guerrier extends Player {
    protected int rage;

    public Guerrier(String nom) {
        this.vie = 120;
        this.degat = 10;
        this.nom = nom;
        this.rage = 150;
        this.nomAbilite1 = "Coup destructeur";
        this.degatAbilite1 = 50;
        this.esquive = 30;
        this.armure = 0;
        this.or = 100;
    }

    public void abilite1(Ennemie e) {
        e.setVie(e.armure(e.getVie() - this.degatAbilite1));
        this.rage -= 100;
        System.out.println(e.getNom() + " perd " + e.armure(e.getVie() - this.degatAbilite1) + " PV. (PV restant : " + e.getVie() + ")");
        System.out.println("Il vous reste " + this.rage + " points de rage.");
    }

    public void abilite2() {
    }
}
