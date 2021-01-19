package fr.training.spring.library.domain;


public class Adresse {

    private int numero;
    private String rue;
    private int zip;
    private String ville;

    public Adresse() {
    }

    public Adresse(int numero, String rue, int zip, String ville) {

        this.numero=numero;
        this.rue=rue;
        this.zip=zip;
        this.ville=ville;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
