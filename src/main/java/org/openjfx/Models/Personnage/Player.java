package org.openjfx.Models.Personnage;

import org.openjfx.Models.Item;

import java.util.List;
import java.util.Scanner;

public abstract class Player extends Personnage{

    private List<Item> Stuff;

    protected int nbPotionVie;
    protected String nomAbilite1;
    protected String nomAbilite2;
    protected int degatAbilite1;
    protected int degatAbilite2;
    protected int or;

    public Player() {
    }

    public void potionVie() {
        this.vie += 20;
    }

    public boolean combat(Ennemie e) {
        while(this.isAlive()) {
            System.out.println("(1)Attaque auto (2)" + this.nomAbilite1 + " (3)" + this.nomAbilite2);
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            int choix = Integer.parseInt(str);
            if (choix == 1) {
                if (e.esquive()) {
                    System.out.println(e.getNom() + " esquive !");
                } else {
                    e.setVie(e.armure(e.getVie() - this.degat));
                    System.out.println(e.getNom() + " perd " + e.armure(e.getVie() - this.degat) + " PV. (PV restant : " + e.getVie() + ")");
                }
            }

            if (choix == 2) {
                this.abilite1(e);
            }

            if (!e.isAlive()) {
                return true;
            }

            if (this.esquive()) {
                System.out.println("Vous esquivez l'attaque !");
            } else {
                this.setVie(this.armure(this.getVie() - e.getDegat()));
                System.out.println("Vous perdez " + this.armure(this.getVie() - e.getDegat()) + " PV. (PV restant : " + this.getVie() + ")");
            }
        }

        return false;
    }

    public abstract void abilite1(Ennemie var1);

    public abstract void abilite2();

    public int getOr() {
        return this.or;
    }

    public void setOr(int or) {
        this.or = or;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public void setEsquive(int esquive) {
        this.esquive = esquive;
    }

    public void setArmure(int armure) {
        this.armure = armure;
    }

    public String getNomAbilite1() {
        return this.nomAbilite1;
    }

    /*
    public boolean boutique() {
        System.out.println("Souhaitez-vous accéder à la boutique du marchand ? (1) Oui (2) Non\n");
        System.out.println(this.degat);
        System.out.println(this.esquive);
        System.out.println(this.or);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int choix = Integer.parseInt(str);
        if (choix == 1) {
            Marchand marchant = new Marchand(false, (String)null, (Evenement)null);
            marchant.afficherBoutique();
            Scanner sc1 = new Scanner(System.in);
            String str1 = sc1.nextLine();
            int choix1 = Integer.parseInt(str1);
            System.out.println(choix1);
            boolean fin = false;

            while(!fin) {
                if (choix1 == 1) {
                    System.out.println("Vous avez acheté " + marchant.getArticle(choix1 - 1).getNom() + " !");
                    this.setDegat(this.degat + marchant.getArticle(choix1 - 1).getDegat());
                    if (this.or < marchant.getArticle(choix1 - 1).getPrix()) {
                        System.out.println("Vous n'avez pas assez d'or pour acheter cet objet.");
                    } else {
                        this.setOr(this.or - marchant.getArticle(choix1 + 1).getPrix());
                    }

                    fin = true;
                }

                if (choix1 == 2) {
                    System.out.println("Vous avez acheté " + marchant.getArticle(choix1 - 1).getNom() + " !");
                    this.setDegat(this.degat + marchant.getArticle(choix1 - 1).getDegat());
                    if (this.or < marchant.getArticle(choix1 - 1).getPrix()) {
                        System.out.println("Vous n'avez pas assez d'or pour acheter cet objet.");
                    } else {
                        this.setOr(this.or - marchant.getArticle(choix1 - 1).getPrix());
                    }

                    fin = true;
                }

                if (choix1 == 3) {
                    System.out.println("Vous avez acheté " + marchant.getArticle(choix1 - 1).getNom() + " !");
                    this.setDegat(this.degat + marchant.getArticle(choix1 - 1).getDegat());
                    this.setEsquive(this.esquive + marchant.getArticle(choix1 - 1).getEsquive());
                    if (this.or < marchant.getArticle(choix1 - 1).getPrix()) {
                        System.out.println("Vous n'avez pas assez d'or pour acheter cet objet.");
                    } else {
                        this.setOr(this.or - marchant.getArticle(choix1 - 1).getPrix());
                    }

                    fin = true;
                }

                if (choix1 == 4) {
                    System.out.println("Vous avez acheté " + marchant.getArticle(choix1 - 1).getNom() + " !");
                    this.setArmure(this.degat + marchant.getArticle(choix1 - 1).getArmure());
                    this.setVie(this.vie + marchant.getArticle(choix1 - 1).getEndurance());
                    this.setEsquive(this.esquive + marchant.getArticle(choix1 - 1).getEsquive());
                    if (this.or < marchant.getArticle(choix1 - 1).getPrix()) {
                        System.out.println("Vous n'avez pas assez d'or pour acheter cet objet.");
                    } else {
                        this.setOr(this.or - marchant.getArticle(choix1 - 1).getPrix());
                    }

                    fin = true;
                }

                if (choix1 == 5) {
                    System.out.println("Vous avez acheté " + marchant.getArticle(choix1 - 1).getNom() + " !");
                    this.setArmure(this.degat + marchant.getArticle(choix1 - 1).getArmure());
                    this.setVie(this.vie + marchant.getArticle(choix1 - 1).getEndurance());
                    this.setEsquive(this.esquive + marchant.getArticle(choix1 - 1).getEsquive());
                    if (this.or < marchant.getArticle(choix1 - 1).getPrix()) {
                        System.out.println("Vous n'avez pas assez d'or pour acheter cet objet.");
                    } else {
                        this.setOr(this.or - marchant.getArticle(choix1 - 1).getPrix());
                    }

                    fin = true;
                }
            }

            System.out.println(this.degat);
            System.out.println(this.esquive);
            System.out.println(this.or);
            return true;
        } else {
            return true;
        }
    }*/
}
