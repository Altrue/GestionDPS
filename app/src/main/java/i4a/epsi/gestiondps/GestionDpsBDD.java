package i4a.epsi.gestiondps;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.LinkedList;

/**
 * Class GestionDpsBDD
 */
public class GestionDpsBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "gestiondps.db";

    private static final String TABLE_FICHE_POSTE = "table_fiche_poste";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOM = "nom";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_DATE_DEBUT = "date_debut";
    private static final int NUM_COL_DATE_DEBUT = 2;
    private static final String COL_DATE_FIN = "date_fin";
    private static final int NUM_COL_DATE_FIN = 3;
    private static final String COL_LIEU = "lieu";
    private static final int NUM_COL_LIEU = 4;
    private static final String COL_NATURE = "nature";
    private static final int NUM_COL_NATURE = 5;
    private static final String COL_EFFECTIF = "effectif";
    private static final int NUM_COL_EFFECTIF = 6;
    private static final String COL_NB_SECOURISTE = "nb_secouriste";
    private static final int NUM_COL_NB_SECOURISTE = 7;
    private static final String COL_DATE_OUVERTURE = "date_ouverture";
    private static final int NUM_COL_DATE_OUVERTURE = 8;
    private static final String COL_DATE_FERMETURE = "date_fermeture";
    private static final int NUM_COL_DATE_FERMETURE = 9;
    private static final String COL_REMARQUES = "remarques";
    private static final int NUM_COL_REMARQUES = 10;
    private static final String COL_DIMENTIONNEMENT = "dimentionnement";
    private static final int NUM_COL_DIMENTIONNEMENT = 11;

    private static final String TABLE_MAIN_COURANTE = "table_main_courante";
    private static final String COL_ID_MC = "id";
    private static final int NUM_COL_ID_MC = 0;
    private static final String COL_HEURE_DEBUT = "heure_debut";
    private static final int NUM_COL_HEURE_DEBUT = 1;
    private static final String COL_HEURE_FIN = "heure_fin";
    private static final int NUM_COL_HEURE_FIN = 2;
    private static final String COL_PRENOM = "prenom";
    private static final int NUM_COL_PRENOM = 3;
    private static final String COL_NOM_MC = "nom";
    private static final int NUM_COL_NOM_MC = 4;
    private static final String COL_SEXE = "sexe";
    private static final int NUM_COL_SEXE = 5;
    private static final String COL_AGE = "age";
    private static final int NUM_COL_AGE = 6;
    private static final String COL_MOTIF = "motif";
    private static final int NUM_COL_MOTIF = 7;
    private static final String COL_SOINS = "soins";
    private static final int NUM_COL_SOINS = 8;
    private static final String COL_REMARQUES_MC = "remarques";
    private static final int NUM_COL_REMARQUES_MC = 9;

    private static final String[] COLUMNS_FICHE_POSTE = {
            COL_ID,
            COL_NOM,
            COL_DATE_DEBUT,
            COL_DATE_FIN,
            COL_LIEU,
            COL_NATURE,
            COL_EFFECTIF,
            COL_NB_SECOURISTE,
            COL_DATE_OUVERTURE,
            COL_DATE_FERMETURE,
            COL_REMARQUES,
            COL_DIMENTIONNEMENT};

    private static final String[] COLUMNS_MAIN_COURANTE = {
            COL_ID_MC,
            COL_HEURE_DEBUT,
            COL_HEURE_FIN,
            COL_PRENOM,
            COL_NOM_MC,
            COL_SEXE,
            COL_AGE,
            COL_MOTIF,
            COL_SOINS,
            COL_REMARQUES_MC};

    private SQLiteDatabase bdd;
    private GestionDpsSQLite gestionDpsSQLite;
    private Context context;

    public GestionDpsBDD(Context context){
        gestionDpsSQLite = new GestionDpsSQLite(context, NOM_BDD, null, VERSION_BDD);
        this.context = context;
    }

    private void openWritable(){
        bdd = gestionDpsSQLite.getWritableDatabase();
    }

    private void openReadable(){
        bdd = gestionDpsSQLite.getReadableDatabase();
    }

    private void close(){
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public void insertFichePoste(FichePoste fichePoste){
        openWritable();

        ContentValues values = new ContentValues();

        if (fichePoste.getId() == -1) {
            values.put(COL_NOM, fichePoste.getNom());
            values.put(COL_DATE_DEBUT, fichePoste.getDateDebut());
            values.put(COL_DATE_FIN, fichePoste.getDateFin());
            values.put(COL_LIEU, fichePoste.getLieu());
            values.put(COL_NATURE, fichePoste.getNature());
            values.put(COL_EFFECTIF, fichePoste.getEffectif());
            values.put(COL_NB_SECOURISTE, fichePoste.getNbSecouriste());
            values.put(COL_DATE_OUVERTURE, fichePoste.getDateOuverture());
            values.put(COL_DATE_FERMETURE, fichePoste.getDateFermeture());
            values.put(COL_REMARQUES, fichePoste.getRemarques());
            values.put(COL_DIMENTIONNEMENT, fichePoste.getDimentionnement());

            bdd.insert(TABLE_FICHE_POSTE, null, values);
        }
        else {
            values.put(COL_ID, fichePoste.getId());
            values.put(COL_NOM, fichePoste.getNom());
            values.put(COL_DATE_DEBUT, fichePoste.getDateDebut());
            values.put(COL_DATE_FIN, fichePoste.getDateFin());
            values.put(COL_LIEU, fichePoste.getLieu());
            values.put(COL_NATURE, fichePoste.getNature());
            values.put(COL_EFFECTIF, fichePoste.getEffectif());
            values.put(COL_NB_SECOURISTE, fichePoste.getNbSecouriste());
            values.put(COL_DATE_OUVERTURE, fichePoste.getDateOuverture());
            values.put(COL_DATE_FERMETURE, fichePoste.getDateFermeture());
            values.put(COL_REMARQUES, fichePoste.getRemarques());
            values.put(COL_DIMENTIONNEMENT, fichePoste.getDimentionnement());

            bdd.update(TABLE_FICHE_POSTE, values, COL_ID + "=" + fichePoste.getId(), null);
        }


        close();
    }

    public void insertMainCourante(MainCourante mainCourante){
        openWritable();

        ContentValues values = new ContentValues();

        values.put(COL_HEURE_DEBUT, mainCourante.getHeureDebut());
        values.put(COL_HEURE_FIN, mainCourante.getHeureFin());
        values.put(COL_PRENOM, mainCourante.getPrenom());
        values.put(COL_NOM_MC, mainCourante.getNom());
        values.put(COL_SEXE, mainCourante.getSexe());
        values.put(COL_AGE, mainCourante.getAge());
        values.put(COL_MOTIF, mainCourante.getMotif());
        values.put(COL_SOINS, mainCourante.getSoins());
        values.put(COL_REMARQUES_MC, mainCourante.getRemarques());

        bdd.insert(TABLE_MAIN_COURANTE, null, values);

        close();
    }

    public void removeFichePoste(FichePoste fichePoste){
        openWritable();
        bdd.delete(TABLE_FICHE_POSTE, COL_ID + " = " + fichePoste.getId(), null);
        close();
    }

    public List<FichePoste> getAllFichesPoste() {
        openReadable();

        bdd = gestionDpsSQLite.getReadableDatabase();
        List<FichePoste> fichesPoste = new LinkedList<>();

        String query = "SELECT  * FROM " + TABLE_FICHE_POSTE;
        Cursor c = bdd.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                fichesPoste.add(cursorToFichePoste(c));
            } while (c.moveToNext());
        }

        close();
        return fichesPoste;
    }

    public List<MainCourante> getAllMainsCourantes() {
        openReadable();

        bdd = gestionDpsSQLite.getReadableDatabase();
        List<MainCourante> mainsCourantes = new LinkedList<>();

        String query = "SELECT  * FROM " + TABLE_MAIN_COURANTE;
        Cursor c = bdd.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                mainsCourantes.add(cursorToMainCourante(c));
            } while (c.moveToNext());
        }

        close();
        return mainsCourantes;
    }

    public FichePoste getOneFichePosteById(int id) {
        openReadable();

        bdd = gestionDpsSQLite.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FICHE_POSTE + " WHERE id = " + id;
        Cursor c = bdd.rawQuery(query, null);
        FichePoste fichePoste = new FichePoste();

        if (c.moveToFirst()) {
            fichePoste = cursorToFichePoste(c);
        }

        close();
        return fichePoste;
    }

    public MainCourante getOneMainCouranteById(int id) {
        openReadable();

        bdd = gestionDpsSQLite.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_MAIN_COURANTE + " WHERE id = " + id;
        Cursor c = bdd.rawQuery(query, null);
        MainCourante mainCourante = new MainCourante();

        if (c.moveToFirst()) {
            mainCourante = cursorToMainCourante(c);
        }

        close();
        return mainCourante;
    }

    /**
     * Transforms a cursor row to a FichePoste object
     *
     * @return FichePoste
     */
    private FichePoste cursorToFichePoste(Cursor c) {

        return new FichePoste(
                c.getInt(NUM_COL_ID),
                c.getString(NUM_COL_NOM),
                c.getString(NUM_COL_DATE_DEBUT),
                c.getString(NUM_COL_DATE_FIN),
                c.getString(NUM_COL_LIEU),
                c.getString(NUM_COL_NATURE),
                c.getInt(NUM_COL_EFFECTIF),
                c.getInt(NUM_COL_NB_SECOURISTE),
                c.getString(NUM_COL_DATE_OUVERTURE),
                c.getString(NUM_COL_DATE_FERMETURE),
                c.getString(NUM_COL_REMARQUES),
                c.getString(NUM_COL_DIMENTIONNEMENT)
        );
    }

    /**
     * Transforms a cursor row to a MainCourante object
     *
     * @return MainCourante
     */
    private MainCourante cursorToMainCourante(Cursor c) {

        return new MainCourante(
                c.getInt(NUM_COL_ID_MC),
                c.getString(NUM_COL_HEURE_DEBUT),
                c.getString(NUM_COL_HEURE_FIN),
                c.getString(NUM_COL_PRENOM),
                c.getString(NUM_COL_NOM_MC),
                c.getString(NUM_COL_SEXE),
                c.getInt(NUM_COL_AGE),
                c.getString(NUM_COL_MOTIF),
                c.getString(NUM_COL_SOINS),
                c.getString(NUM_COL_REMARQUES_MC)
        );
    }
}
