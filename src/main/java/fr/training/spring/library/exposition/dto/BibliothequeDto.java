package fr.training.spring.library.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.Adresse;
import fr.training.spring.library.domain.Directeur;
import fr.training.spring.library.domain.TypeDeBibliotheque;

import java.lang.reflect.Type;
import java.util.List;

public class BibliothequeDto {


    @JsonProperty
    private long id;
    @JsonProperty
    private TypeDeBibliotheque type;
    @JsonProperty
    private DirecteurDto directeur;
    @JsonProperty
    private AdresseDto adresse;
    @JsonProperty
    private List<LivreDto> catalogue;

    public BibliothequeDto(long id, TypeDeBibliotheque type, DirecteurDto directeur, AdresseDto adresse, List<LivreDto> catalogue) {
        this.id = id;
        this.type = type;
        this.directeur = directeur;
        this.adresse = adresse;
        this.catalogue = catalogue;
    }


}
