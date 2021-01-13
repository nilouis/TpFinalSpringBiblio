package fr.training.spring.library.entity;

import javax.persistence.*;

@Entity
public class Bibliotheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated
    private TypeDeBibliotheque type;

    @Embedded
    private Adresse adresse;

    @Embedded
    private Directeur direceteur;

    public Bibliotheque(long id, TypeDeBibliotheque type, Adresse adresse, Directeur direceteur) {
        this.id = id;
        this.type = type;
        this.adresse = adresse;
        this.direceteur = direceteur;
    }

    public Bibliotheque(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Directeur getDireceteur() {
        return direceteur;
    }

    public void setDireceteur(Directeur direceteur) {
        this.direceteur = direceteur;
    }
}
