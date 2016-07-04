package i4a.epsi.gestiondps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Class GestionDpsBDD
 */
public class GestionDpsBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "photos.db";

    private static final String TABLE_FICHE_POSTE = "table_fiche_poste";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NUM = "num";
    private static final int NUM_COL_NUM = 1;
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

    private static final String TABLE_DIMENTIONNEMENT = "table_dimentionnement";
    private static final String COL_LIBELLE = "libelle";
    private static final int NUM_COL_LIBELLE = 1;

    private static final String[] COLUMNS_FICHE_POSTE = {
            COL_ID,
            COL_NUM,
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

    private static final String[] COLUMNS_DIMENTIONNEMENT = {
            COL_ID,
            COL_LIBELLE};

    private SQLiteDatabase bdd;
    private FichePosteSQLite fichePosteSQLite;
    private DimentionnementSQLite dimentionnementSQLite;
    private Context context;

    public GestionDpsBDD(Context context){
        fichePosteSQLite = new FichePosteSQLite(context, NOM_BDD, null, VERSION_BDD);
        dimentionnementSQLite = new DimentionnementSQLite(context, NOM_BDD, null, VERSION_BDD);
        this.context = context;
    }

    private void openWritable(){
        bdd = fichePosteSQLite.getWritableDatabase();
        bdd = dimentionnementSQLite.getWritableDatabase();
    }

    private void openReadable(){
        bdd = fichePosteSQLite.getReadableDatabase();
        bdd = dimentionnementSQLite.getReadableDatabase();
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

        values.put(COL_NUM, fichePoste.getNum());
        values.put(COL_DATE_DEBUT, fichePoste.getDateDebut().toString());
        values.put(COL_DATE_FIN, fichePoste.getDateFin().toString());
        values.put(COL_LIEU, fichePoste.getLieu());
        values.put(COL_NATURE, fichePoste.getNature());
        values.put(COL_EFFECTIF, fichePoste.getEffectif());
        values.put(COL_NB_SECOURISTE, fichePoste.getNbSecouriste());
        values.put(COL_DATE_OUVERTURE, fichePoste.getDateOuverture().toString());
        values.put(COL_DATE_FERMETURE, fichePoste.getDateFermeture().toString());
        values.put(COL_REMARQUES, fichePoste.getRemarques());
        values.put(COL_DIMENTIONNEMENT, fichePoste.getDimentionnement().getId());

        bdd.insert(TABLE_FICHE_POSTE, null, values);

        close();
    }

    public void removeFichePoste(FichePoste fichePoste){
        openWritable();
        bdd.delete(TABLE_FICHE_POSTE, COL_ID + " = " + fichePoste.getId(), null);
        close();
    }

    public List<FichePoste> getAllFichesPoste() {
        openReadable();

        bdd = fichePosteSQLite.getReadableDatabase();
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

    public Dimentionnement getOneDimentionnementById(int id) {
        openReadable();

        bdd = fichePosteSQLite.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_DIMENTIONNEMENT + "WHERE id = " + id;
        Cursor c = bdd.rawQuery(query, null);
        Dimentionnement dimentionnement = new Dimentionnement();

        if (c.moveToFirst()) {
            dimentionnement = cursorToDimentionnement(c);
        }

        close();
        return dimentionnement;
    }

    public FichePoste getOneFichePosteById(int id) {
        openReadable();

        bdd = fichePosteSQLite.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FICHE_POSTE + "WHERE id = " + id;
        Cursor c = bdd.rawQuery(query, null);
        FichePoste fichePoste = new FichePoste();

        if (c.moveToFirst()) {
            fichePoste = cursorToFichePoste(c);
        }

        close();
        return fichePoste;
    }

    /**
     * Transforms a cursor row to a FichePoste object
     *
     * @return FichePoste
     */
    private FichePoste cursorToFichePoste(Cursor c) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS", Locale.getDefault());
        Date dateDebut = new Date();
        Date dateFin = new Date();
        Date dateOuverture = new Date();
        Date dateFermeture = new Date();

        try {
            dateDebut = sdf.parse(c.getString(NUM_COL_DATE_DEBUT));
            dateFin = sdf.parse(c.getString(NUM_COL_DATE_FIN));
            dateOuverture = sdf.parse(c.getString(NUM_COL_DATE_OUVERTURE));
            dateFermeture = sdf.parse(c.getString(NUM_COL_DATE_FERMETURE));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return new FichePoste(
                c.getInt(NUM_COL_ID),
                c.getInt(NUM_COL_NUM),
                dateDebut,
                dateFin,
                c.getString(NUM_COL_LIEU),
                c.getString(NUM_COL_NATURE),
                c.getInt(NUM_COL_EFFECTIF),
                c.getInt(NUM_COL_NB_SECOURISTE),
                dateOuverture,
                dateFermeture,
                c.getString(NUM_COL_REMARQUES),
                getOneDimentionnementById(c.getInt(NUM_COL_DIMENTIONNEMENT))
        );
    }

    /**
     * Transforms a cursor row to a Dimentionnement object
     *
     * @return Dimentionnement
     */
    private Dimentionnement cursorToDimentionnement(Cursor c) {
        return new Dimentionnement(
                c.getInt(NUM_COL_ID),
                c.getString(NUM_COL_LIBELLE)
        );
    }
}
