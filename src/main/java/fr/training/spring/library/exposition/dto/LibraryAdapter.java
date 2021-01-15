package fr.training.spring.library.exposition.dto;

import fr.training.spring.library.domain.Bibliotheque;
import fr.training.spring.library.domain.Livre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryAdapter{

    public static List<LightBibliothequeDto> mapToLightBibliothequeDtoListe(List<Bibliotheque> bibliotheques) {

        List<LightBibliothequeDto> bibliothequesLightDto = new ArrayList<LightBibliothequeDto>();
        bibliotheques.stream().map(b->bibliothequesLightDto.add(mapToLightDto(b))).collect(Collectors.toList());

        return bibliothequesLightDto;
    }

    private static LightBibliothequeDto mapToLightDto(Bibliotheque biblio) {

        return new LightBibliothequeDto(biblio.getId(),
                                        biblio.getType(),
                                        new DirecteurDto(biblio.getDirecteur().getNom(),biblio.getDirecteur().getPrenom()),
                                        new AdresseDto(biblio.getAdresse().getNumero(),biblio.getAdresse().getRue(),biblio.getAdresse().getZip(),biblio.getAdresse().getVille()));

    }

    public static List<BibliothequeDto> mapToBibliothqueDtoListe(List<Bibliotheque> bibliotheques){
        List<BibliothequeDto> bibliothequeDtos = new ArrayList<BibliothequeDto>();
        bibliotheques.stream().map(b->bibliothequeDtos.add(mapToDto(b))).collect(Collectors.toList());

        return bibliothequeDtos;
    }

    public static BibliothequeDto mapToDto(Bibliotheque b) {

        return new BibliothequeDto(b.getId(),
                b.getType(),
                new DirecteurDto(b.getDirecteur().getNom(), b.getDirecteur().getPrenom()),
                new AdresseDto(b.getAdresse().getNumero(), b.getAdresse().getRue(), b.getAdresse().getZip(), b.getAdresse().getVille()),
                mapCatalogueToDTO(b.getCatalogue())
                );

    }

    private static List<LivreDto> mapCatalogueToDTO(List<Livre> catalogue) {

        List<LivreDto> livreDtos= new ArrayList<LivreDto>();
        catalogue.stream().map(livre->livreDtos.add(new LivreDto(livre.getIsbn(), livre.getTitre(), livre.getAuteur(), livre.getNombreDePage() , livre.getGenre()))).collect(Collectors.toList());
        return livreDtos;
    }


}
