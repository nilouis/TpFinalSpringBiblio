package fr.training.spring.library.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.Adresse;
import fr.training.spring.library.domain.Directeur;
import fr.training.spring.library.domain.TypeDeBibliotheque;

public class LightBibliothequeDto {

    @JsonProperty
    private long id;
    @JsonProperty
    private TypeDeBibliotheque type;
    @JsonProperty
    private DirecteurDto directeur;
    @JsonProperty
    private AdresseDto adresse;

    public LightBibliothequeDto(long id, TypeDeBibliotheque type, DirecteurDto directeur, AdresseDto adresse) {
        this.id = id;
        this.type = type;
        this.directeur = directeur;
        this.adresse = adresse;
    }
}
