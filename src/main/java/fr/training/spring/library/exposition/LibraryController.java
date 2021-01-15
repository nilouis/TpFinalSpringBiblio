package fr.training.spring.library.exposition;

import fr.training.spring.library.domain.Bibliotheque;
import fr.training.spring.library.domain.Livre;
import fr.training.spring.library.domain.TypeDeBibliotheque;
import fr.training.spring.library.exposition.dto.BibliothequeDto;
import fr.training.spring.library.exposition.dto.LibraryAdapter;
import fr.training.spring.library.exposition.dto.LightBibliothequeDto;
import fr.training.spring.library.service.BibliothequeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class LibraryController {

    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    private final BibliothequeService bibliothequeService;

    private Long newId;

    public LibraryController(BibliothequeService bibliothequeService) {
        this.bibliothequeService = bibliothequeService;
    }

    //Créer une nouvelle biblio
    @ApiOperation(value = "Opérations permettant de créer une nouvelle biliothèque", nickname = "addBibliotheque", notes = "Saisir les infos d'une bibliotheque, sinon une biblitheque avec des infos par défaut sera créé")
    @PostMapping(value = "/bibliotheque", produces = { "application/json" })
    public ResponseEntity<URI> postBibliotheque(@RequestBody final Bibliotheque newBibliotheque){

        bibliothequeService.creerNouvelleBibliothque(newBibliotheque);
        newId = newBibliotheque.getId();

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //Afficher le détail d'une bibliothque si elle existe bien
    @ApiOperation(value = "Afficher le détail d'une bibliothèque si elle existe", nickname = "findBibliotheque", notes = "Affiche une bibliothque à partir de son identifiant")
    @GetMapping(value = "/bibliotheque/{id}", produces = { "application/json" })
    public BibliothequeDto getCatalogueDeBibliotheque(@PathVariable(value = "id") final long idATrouver){

        return  LibraryAdapter.mapToDto(bibliothequeService.chercherBibliotheque(idATrouver));
    }

    //Lister toutes les bibliotheques
    @ApiOperation(value = "Afficher la liste de toutes les bibliothèques", nickname = "findAllBibliotheque", notes = "La liste de toutes les bibliothèque")
    @GetMapping(value = "/bibliotheques", produces = { "application/json" })
    public List<LightBibliothequeDto> getListeToutesBibliotheques(){
        List<Bibliotheque> bibliothequeListe = bibliothequeService.chercherTouteLesBibliotheques();
        return  LibraryAdapter.mapToLightBibliothequeDtoListe(bibliothequeListe);
    }

    //Lister les bibliotheque d'un certain type
    @ApiOperation(value = "Afficher la liste de toutes les bibliothèques d'un type donné", nickname = "findAllBibliothequeByType", notes = "La liste de toutes les bibliothèque pour un type donné")
    @GetMapping(value = "/bibliotheques/type/{type}", produces = { "application/json" })
    public List<LightBibliothequeDto> getListeTypeBibliotheque(@PathVariable(value="type") final TypeDeBibliotheque typeAChercher){
        List<Bibliotheque> bibliothequeListe = bibliothequeService.chercherBibliothequeParType(typeAChercher);
        return LibraryAdapter.mapToLightBibliothequeDtoListe(bibliothequeListe);
    }

    //Lister les bibliothèque d'un directeur
    @ApiOperation(value = "Afficher la liste de toutes les bibliothèques d'un directeur", nickname = "findAllBibliothequeByName", notes = "La liste de toutes les bibliothèque pour un directeur (nom) donné")
    @GetMapping(value = "/bibliotheques/director/{directorName}", produces = { "application/json" })
    public List<LightBibliothequeDto> getListeBibliothqueDirecteur(@PathVariable(value="directorName") final String nomAChercher){
        List<Bibliotheque> bibliothequeListe = bibliothequeService.chercherBibliothequeParNomDeDirecteur(nomAChercher);
        return LibraryAdapter.mapToLightBibliothequeDtoListe(bibliothequeListe);
    }

    //Mettre à jour une bibliotheque
    @ApiOperation(value = "Mettre à jour une bibliothèque donnée", nickname = "updateGivenLibrary", notes = "Met à jour une bibliothèque existante")
    @PutMapping(value = "/bibliotheque", produces = { "application/json" })
    public void putBibliotheque(@RequestBody final Bibliotheque bibliothqueAMAJ){
        bibliothequeService.updateBiliotheque(bibliothqueAMAJ);
    }

    //Supprime une Bibliotheque
    @ApiOperation(value="Supprimer une bibliothèque pour un identifiant donné", nickname = "deleteBibliothequeById", notes="Supprime la bibliothque pour un id donné")
    @DeleteMapping(value="/bibliotheque/{id}", produces = { "application/json" })
    public void deleteBibliothequeById(@PathVariable(value="id") final long idASupprimer){
        bibliothequeService.deleteBibliotheque(idASupprimer);
    }


    //Ajoute un livre dans une bibliothèque
    @ApiOperation(value="Ajouter un livre dans une bibliothèque", nickname = "addLivre", notes="Ajoute un livre dans une bibliothèque")
    @PostMapping(value = "/bibliotheque/livre", produces = {"application/json"})
    public ResponseEntity<Livre> postNouveauLivre(@RequestBody final Bibliotheque bibliothequeARemplir){

        bibliothequeService.ajouterLivre(bibliothequeARemplir);
        Livre newLivre=bibliothequeARemplir.getCatalogue().get(0);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newLivre.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //Dans quelle bibliothque peut on trouver un livre
    @ApiOperation(value="Obtenir les infos d'un livre", nickname = "getLivre", notes="Retrouve les infos d'un livre")
    @GetMapping(value = "/bibliotheque/livre/{id}", produces = {"application/json"})
    public Livre getInfoLivre(@PathVariable(value="id") final long idDeLivre){

        return bibliothequeService.rechercherLivre(idDeLivre);

    }
}
