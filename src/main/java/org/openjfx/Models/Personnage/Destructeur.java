package org.openjfx.Models.Personnage;

public class Destructeur extends Ennemie{
    public Destructeur() {
        this.vie = 70 + (int)(Math.random() * 20.0D);
        this.degat = 25;
        this.nom = "Destructeur";
        this.esquive = 15;
        this.armure = 30;
    }

    public String criArrivee() {
        return "";
    }

    public String criMort() {
        return "";
    }

    public void loot(Player p) {
        int loot = 2 + (int)(Math.random() * 5.0D);
        p.setOr(p.getOr() + loot);
        System.out.println("Vous ramassez " + loot + " or.");
    }
}
