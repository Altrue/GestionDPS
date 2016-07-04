package i4a.epsi.gestiondps;

import java.io.Serializable;

/**
 * Class FichePoste
 */
public class FichePoste implements Serializable {

    private int id;
    private String nom;
    private String dateDebut;
    private String dateFin;
    private String lieu;
    private String nature;
    private int effectif;
    private int nbSecouriste;
    private String dateOuverture;
    private String dateFermeture;
    private String remarques;
    private String dimentionnement;

    public FichePoste(
            int id,
            String nom,
            String dateDebut,
            String dateFin,
            String lieu,
            String nature,
            int effectif,
            int nbSecouriste,
            String dateOuverture,
            String dateFermeture,
            String remarques,
            String dimentionnement) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.nature = nature;
        this.effectif = effectif;
        this.nbSecouriste = nbSecouriste;
        this.dateOuverture = dateOuverture;
        this.dateFermeture = dateFermeture;
        this.remarques = remarques;
        this.dimentionnement = dimentionnement;
    }

    public FichePoste() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    public int getNbSecouriste() {
        return nbSecouriste;
    }

    public void setNbSecouriste(int nbSecouriste) {
        this.nbSecouriste = nbSecouriste;
    }

    public String getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(String dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public String getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(String dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public String getDimentionnement() {
        return dimentionnement;
    }

    public void setDimentionnement(String dimentionnement) {
        this.dimentionnement = dimentionnement;
    }

    @Override
    public String toString() {
        return "FichePoste{" +
                "id=" + id +
                ", nom=" + nom +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin=" + dateFin +
                ", lieu='" + lieu + '\'' +
                ", nature='" + nature + '\'' +
                ", effectif=" + effectif +
                ", nbSecouriste=" + nbSecouriste +
                ", dateOuverture=" + dateOuverture +
                ", dateFermeture=" + dateFermeture +
                ", remarques='" + remarques + '\'' +
                ", dimentionnement='" + dimentionnement + '\'' +
                '}';
    }
}
