package fr.training.spring.library.infrastructure.entity;

import fr.training.spring.library.domain.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="Bibliotheque")
public class BibliothequeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Enumerated(EnumType.STRING)
    private TypeDeBibliotheque type;

    @Column(name="numero")
    private int numeroAdresse;
    @Column(name="zip")
    private int zipAdresse;
    @Column(name="rue")
    private String rueAdresse;
    @Column(name="ville")
    private String villeAdresse;

    @Column(name="nom")
    private String directeurNom;
    @Column(name="prenom")
    private String directeurPrenom;

    @ManyToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
    private List<LivreEntity> catalogue;

    public BibliothequeEntity() {
    }
    public BibliothequeEntity(long id, TypeDeBibliotheque type, int numeroAdresse, int zipAdresse, String rueAdresse, String villeAdresse, String directeurNom, String directeurPrenom, List<LivreEntity> catalogue) {
        this.id = id;
        this.type = type;
        this.numeroAdresse = numeroAdresse;
        this.zipAdresse = zipAdresse;
        this.rueAdresse = rueAdresse;
        this.villeAdresse = villeAdresse;
        this.directeurNom = directeurNom;
        this.directeurPrenom = directeurPrenom;
        this.catalogue = catalogue;
    }
    public BibliothequeEntity(Bibliotheque newBibliotheque) {
        this.id= newBibliotheque.getId();
        this.type=newBibliotheque.getType();
        this.directeurNom=newBibliotheque.getDirecteur().getNom();
        this.directeurPrenom=newBibliotheque.getDirecteur().getPrenom();
        this.numeroAdresse=newBibliotheque.getAdresse().getNumero();
        this.rueAdresse=newBibliotheque.getAdresse().getRue();
        this.zipAdresse=newBibliotheque.getAdresse().getZip();
        this.villeAdresse=newBibliotheque.getAdresse().getVille();

        List<LivreEntity> newCatalogue= (List<LivreEntity>) newBibliotheque.getCatalogue().stream().map(livre->new LivreEntity(livre)).collect(Collectors.toList());
        this.catalogue=newCatalogue;
    }

    public long getId() {
        return id;
    }

    public TypeDeBibliotheque getType() {
        return type;
    }

    public int getNumeroAdresse() {
        return numeroAdresse;
    }

    public int getZipAdresse() {
        return zipAdresse;
    }

    public String getRueAdresse() {
        return rueAdresse;
    }

    public String getVilleAdresse() {
        return villeAdresse;
    }

    public String getDirecteurNom() {
        return directeurNom;
    }

    public String getDirecteurPrenom() {
        return directeurPrenom;
    }

    public List<LivreEntity> getCatalogue() {
        return catalogue;
    }

    public Bibliotheque convertFromEntity() {

        Directeur directeur=new Directeur();
        directeur.setNom(this.getDirecteurNom());
        directeur.setPrenom(this.getDirecteurPrenom());

        Adresse adresse = new Adresse();
        adresse.setNumero(this.getNumeroAdresse());
        adresse.setRue(this.getRueAdresse());
        adresse.setVille(this.getVilleAdresse());
        adresse.setZip(this.getZipAdresse());

        Bibliotheque entityConvertie = new Bibliotheque();
        entityConvertie.setId(this.id);
        entityConvertie.setDireceteur(directeur);
        entityConvertie.setAdresse(adresse);
        entityConvertie.setType(this.type);
        entityConvertie.setCatalogue(this.catalogue.stream().map(livreEntity->livreEntity.toLivre()).collect(Collectors.toList()));
        return entityConvertie;
    }
}
