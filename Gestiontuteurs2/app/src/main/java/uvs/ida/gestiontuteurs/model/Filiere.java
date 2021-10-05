package uvs.ida.gestiontuteurs.model;

import java.io.Serializable;

/**
 * Created by eldji on 3/18/21
 */
public class Filiere implements Serializable {

    private int idFiliere;
    private String libelle;

    public Filiere(){

    }

    public Filiere(int idFiliere, String libelle) {
        this.idFiliere = idFiliere;
        this.libelle = libelle;
    }

    public Filiere( String libelle) {
        this.libelle = libelle;
    }

    public int getIdFiliere() {
        return idFiliere;
    }

    public void setIdFiliere(int idFiliere) {
        this.idFiliere = idFiliere;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return this.libelle;
    }
}
