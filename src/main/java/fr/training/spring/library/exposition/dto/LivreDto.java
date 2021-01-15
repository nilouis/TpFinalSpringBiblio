package fr.training.spring.library.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.Genre;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class LivreDto {


    @JsonProperty
    private String  isbn;

    @JsonProperty
    private String titre;

    @JsonProperty
    private String auteur;

    @JsonProperty
    private int nombreDePage;

    @JsonProperty
    private Genre genre;

    public LivreDto(String isbn, String titre, String auteur, int nombreDePage, Genre genre) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.nombreDePage = nombreDePage;
        this.genre = genre;
    }
}
