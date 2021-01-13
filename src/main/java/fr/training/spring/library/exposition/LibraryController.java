package fr.training.spring.library.exposition;

import fr.training.spring.library.entity.Adresse;
import fr.training.spring.library.entity.Bibliotheque;
import fr.training.spring.library.entity.Directeur;
import fr.training.spring.library.entity.TypeDeBibliotheque;
import fr.training.spring.library.service.BibliothequeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
        if(newBibliotheque!=null){
            bibliothequeService.creerNouvelleBibliothque(newBibliotheque);
            newId = newBibliotheque.getId();
        }else{
            Adresse defaultAddresse = new Adresse();
            defaultAddresse.setNumero(0);
            defaultAddresse.setRue("N/A");
            defaultAddresse.setVille("N/A");
            defaultAddresse.setZip(00000);

            Directeur defaultDirecteur=new Directeur();
            defaultDirecteur.setNom("Nom1");
            defaultDirecteur.setPrenom("Nom2");

            Bibliotheque defaultBibliotheque=new Bibliotheque();
            defaultBibliotheque.setAdresse(defaultAddresse);
            defaultBibliotheque.setDireceteur(defaultDirecteur);
            defaultBibliotheque.setType(TypeDeBibliotheque.Publique);

            bibliothequeService.creerNouvelleBibliothque(defaultBibliotheque);
            newId=defaultBibliotheque.getId();
        }

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //Afficher le détail d'une bibliothque si elle existe bien
    public void getCatalogueDeBibliotheque(/*id*/){

    }

    //Lister toutes les bibliotheques
    public void getListeBibliotheque(){

    }

    //Lister les bibliotheque d'un certain type
    public void getListeTypeBibliotheque(/*type*/){

    }

    //Lister les bibliothèque d'un directeur
    public void getListeBibliothqueDirecteur(/*prenom*/){

    }

    //Mettre à jour une bibliotheque
    public void patchBibliotheque(/*id*/){

    }

    //Mettre à jour une bibliotheque si elle existe
    public void patchBibliothequeVerif(/*id*/){

    }
}
