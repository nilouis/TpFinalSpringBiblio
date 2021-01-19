package fr.training.spring.library.domain;

import javax.persistence.*;
import java.util.List;


public class Livre {

    private long id;

    private String  isbn;

    private String titre;

    private String auteur;

    private int nombreDePage;

    private Genre genre;

    public Livre() {
    }

    public Livre(Object o, String s, String spring_in_action, String craig_walls, int i, Genre amour) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getNombreDePage() {
        return nombreDePage;
    }

    public void setNombreDePage(int nombreDePage) {
        this.nombreDePage = nombreDePage;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
