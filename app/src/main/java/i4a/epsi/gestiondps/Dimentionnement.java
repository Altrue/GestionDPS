package i4a.epsi.gestiondps;

import java.io.Serializable;

/**
 * Class Dimentionnement
 */
public class Dimentionnement implements Serializable {

    private int id;
    private String libelle;

    public Dimentionnement(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Dimentionnement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Dimentionnement{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
