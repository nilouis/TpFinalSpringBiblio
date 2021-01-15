package fr.training.spring.library.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirecteurDto {

    @JsonProperty
    private String nom;
    @JsonProperty
    private String prenom;

    public DirecteurDto(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
}
