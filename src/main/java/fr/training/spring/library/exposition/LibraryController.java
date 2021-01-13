package fr.training.spring.library.exposition;

import fr.training.spring.library.entity.Bibliotheque;
import fr.training.spring.library.entity.TypeDeBibliotheque;
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
    public Bibliotheque getCatalogueDeBibliotheque(@PathVariable(value = "id") final long idATrouver){

        return  bibliothequeService.chercherBibliotheque(idATrouver);
    }

    //Lister toutes les bibliotheques
    @ApiOperation(value = "Afficher la liste de toutes les bibliothèques", nickname = "findAllBibliotheque", notes = "La liste de toutes les bibliothèque")
    @GetMapping(value = "/bibliotheques/", produces = { "application/json" })
    public List<Bibliotheque> getListeToutesBibliotheques(){
        return  bibliothequeService.chercherTouteLesBibliotheques();
    }

    //Lister les bibliotheque d'un certain type
    @ApiOperation(value = "Afficher la liste de toutes les bibliothèques d'un type donné", nickname = "findAllBibliothequeByType", notes = "La liste de toutes les bibliothèque pour un type donné")
    @GetMapping(value = "/bibliotheques/type/{type}", produces = { "application/json" })
    public List<Bibliotheque> getListeTypeBibliotheque(@PathVariable(value="type") final TypeDeBibliotheque typeAChercher){
        return bibliothequeService.chercherBibliothequeParType(typeAChercher);
    }

    //Lister les bibliothèque d'un directeur
    @ApiOperation(value = "Afficher la liste de toutes les bibliothèques d'un directeur", nickname = "findAllBibliothequeByName", notes = "La liste de toutes les bibliothèque pour un directeur (nom) donné")
    @GetMapping(value = "/bibliotheques/director/{directorName}", produces = { "application/json" })
    public List<Bibliotheque> getListeBibliothqueDirecteur(@PathVariable(value="directorName") final String nomAChercher){
        return bibliothequeService.chercherBibliothequeParNomDeDirecteur(nomAChercher);
    }

    //Mettre à jour une bibliotheque
    @ApiOperation(value = "Mettre à jour une bibliothèque donnée", nickname = "updateGivenLibrary", notes = "Met à jour une bibliothèque existante")
    @PatchMapping(value = "/bibliotheque/", produces = { "application/json" })
    public void patchBibliotheque(@RequestBody final Bibliotheque bibliothqueAMAJ){
        bibliothequeService.updateBiliotheque(bibliothqueAMAJ);
    }

    //Mettre à jour une bibliotheque si elle existe
    public void patchBibliothequeVerif(/*id*/){

    }
}
