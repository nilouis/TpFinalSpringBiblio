package fr.training.spring.library.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Directeur {

    private String nom;
    private String prenom;

    public Directeur() {
    }

    public Directeur(String nom, String prenom) {
        this.nom=nom;
        this.prenom=prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
