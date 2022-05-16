package org.openjfx.Models.Personnage;

public class Mage extends Player {
    protected int mana;
    protected int nbPotionMana;
    protected int degatSort;

    public Mage(String nom) {
        this.vie = 80;
        this.degat = this.degat;
        this.nom = nom;
        this.mana = this.mana;
    }

    public void potionMana() {
        this.mana += 15;
    }

    public void abilite1(Ennemie e) {
        e.setVie(e.getVie() - this.degatAbilite1);
        this.mana -= 100;
        System.out.println(e.getNom() + " perd " + this.degatAbilite1 + " PV.");
    }

    public void abilite2() {
    }
}
