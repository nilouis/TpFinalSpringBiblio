package fr.training.spring.library.infrastructure.entity;

import fr.training.spring.library.domain.Genre;
import fr.training.spring.library.domain.Livre;

import javax.persistence.*;

@Entity
@Table(name = "Livre")
public class LivreEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="isbn")
    private String  isbn;

    @Column(name="titre")
    private String titre;

    @Column(name="auteur")
    private String auteur;

    @Column(name="nbPage")
    private int nombreDePage;

    @Enumerated(EnumType.STRING)
    @Column(name="genre")
    private Genre genre;

    public LivreEntity(){
    }

    public LivreEntity(long id, String isbn, String titre, String auteur, int nombreDePage, Genre genre) {
        this.id = id;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.nombreDePage = nombreDePage;
        this.genre = genre;
    }

    public LivreEntity(Livre livre) {
        this.id = livre.getId();
        this.isbn = livre.getIsbn();
        this.titre = livre.getTitre();
        this.auteur = livre.getAuteur();
        this.nombreDePage = livre.getNombreDePage();
        this.genre = livre.getGenre();
    }

    public long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getNombreDePage() {
        return nombreDePage;
    }

    public Genre getGenre() {
        return genre;
    }

    public Livre toLivre() {
        Livre newLivre = new Livre();
        newLivre.setId(this.id);
        newLivre.setAuteur(this.auteur);
        newLivre.setGenre(this.genre);
        newLivre.setIsbn(this.isbn);
        newLivre.setNombreDePage(this.nombreDePage);
        return newLivre;
    }
}

