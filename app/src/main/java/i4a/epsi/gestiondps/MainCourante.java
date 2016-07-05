package i4a.epsi.gestiondps;

import java.io.Serializable;

/**
 * Class MainCourante
 */
public class MainCourante implements Serializable {

    private int id;
    private String heureDebut;
    private String heureFin;
    private String prenom;
    private String nom;
    private String sexe;
    private int age;
    private String motif;
    private String soins;
    private String remarques;

    public MainCourante(
            int id,
            String heureDebut,
            String heureFin,
            String prenom,
            String nom,
            String sexe,
            int age,
            String motif,
            String soins,
            String remarques) {
        this.id = id;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.motif = motif;
        this.soins = soins;
        this.remarques = remarques;
    }

    public MainCourante() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getSoins() {
        return soins;
    }

    public void setSoins(String soins) {
        this.soins = soins;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    @Override
    public String toString() {
        return "MainCourante{" +
                "id=" + id +
                ", heureDebut='" + nom + '\'' +
                ", heureFin='" + heureFin + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", age=" + age +
                ", motif='" + motif + '\'' +
                ", soins='" + soins + '\'' +
                ", remarques='" + remarques + '\'' +
                '}';
    }
}
