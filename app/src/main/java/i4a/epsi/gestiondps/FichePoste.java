package i4a.epsi.gestiondps;

import java.io.Serializable;
import java.util.Date;

/**
 * Class FichePoste
 */
public class FichePoste implements Serializable {

    private int id;
    private int num;
    private Date dateDebut;
    private Date dateFin;
    private String lieu;
    private String nature;
    private int effectif;
    private int nbSecouriste;
    private Date dateOuverture;
    private Date dateFermeture;
    private String remarques;
    private Dimentionnement dimentionnement;

    public FichePoste(
            int id,
            int num,
            Date dateDebut,
            Date dateFin,
            String lieu,
            String nature,
            int effectif,
            int nbSecouriste,
            Date dateOuverture,
            Date dateFermeture,
            String remarques,
            Dimentionnement dimentionnement) {
        this.id = id;
        this.num = num;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
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

    public Date getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Date getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public Dimentionnement getDimentionnement() {
        return dimentionnement;
    }

    public void setDimentionnement(Dimentionnement dimentionnement) {
        this.dimentionnement = dimentionnement;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", num=" + num +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin=" + dateFin +
                ", lieu='" + lieu + '\'' +
                ", nature='" + nature + '\'' +
                ", effectif=" + effectif +
                ", nbSecouriste=" + nbSecouriste +
                ", dateOuverture=" + dateOuverture +
                ", dateFermeture=" + dateFermeture +
                ", remarques='" + remarques + '\'' +
                ", dimentionnement=" + dimentionnement +
                '}';
    }
}
