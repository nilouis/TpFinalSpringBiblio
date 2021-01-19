package fr.training.spring.library.domain;

import javax.persistence.*;
import java.util.List;

public class Bibliotheque {


    private long id;

    private TypeDeBibliotheque type;

    private Adresse adresse;

    private Directeur directeur;

    private List<Livre> catalogue;

    public Bibliotheque(long id, TypeDeBibliotheque type, Adresse adresse, Directeur directeur) {
        this.id = id;
        this.type = type;
        this.adresse = adresse;
        this.directeur = directeur;
    }

    public Bibliotheque(){

    }

    public <T> Bibliotheque(Object o, Object o1, Adresse adresse, Directeur directeur, List<T> asList) {
    }

    public Directeur getDirecteur() {
        return directeur;
    }

    public void setDirecteur(Directeur directeur) {
        this.directeur = directeur;
    }

    public List<Livre> getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(List<Livre> catalogue) {
        this.catalogue = catalogue;
    }


    public long getId() {
        return id;
    }
    public void setId(long id){ this.id=id;}

    public TypeDeBibliotheque getType() {
        return type;
    }

    public void setType(TypeDeBibliotheque type) {
        this.type = type;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setDireceteur(Directeur direceteur) {
        this.directeur = direceteur;
    }

    public void update(Bibliotheque bibliothqueAMAJ) {
        this.setType(bibliothqueAMAJ.getType());
        this.setDireceteur(bibliothqueAMAJ.getDirecteur());
        this.setAdresse(bibliothqueAMAJ.getAdresse());
    }

    public void ajouterLivreAuCatalogue(Livre newLivre) {
        this.catalogue.add(newLivre);
    }
}
