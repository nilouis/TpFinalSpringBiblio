package fr.training.spring.library.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdresseDto {

    @JsonProperty
    private int numero;
    @JsonProperty
    private String rue;
    @JsonProperty
    private int zip;
    @JsonProperty
    private String ville;

    public AdresseDto(int numero, String rue, int zip, String ville) {
        this.numero = numero;
        this.rue = rue;
        this.zip = zip;
        this.ville = ville;
    }
}
