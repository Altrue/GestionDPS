package i4a.epsi.gestiondps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Class GestionDpsSQLite
 */
public class GestionDpsSQLite extends SQLiteOpenHelper {

    private static final String TABLE_FICHE_POSTE = "table_fiche_poste";
    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_DATE_DEBUT = "date_debut";
    private static final String COL_DATE_FIN = "date_fin";
    private static final String COL_LIEU = "lieu";
    private static final String COL_NATURE = "nature";
    private static final String COL_EFFECTIF = "effectif";
    private static final String COL_NB_SECOURISTE = "nb_secouriste";
    private static final String COL_DATE_OUVERTURE = "date_ouverture";
    private static final String COL_DATE_FERMETURE = "date_fermeture";
    private static final String COL_REMARQUES = "remarques";
    private static final String COL_DIMENTIONNEMENT = "dimentionnement";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_FICHE_POSTE + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOM + " INTEGER NULL, "
            + COL_DATE_DEBUT + " VARCHAR(50) NULL, " + COL_DATE_FIN + " VARCHAR(50) NULL, "
            + COL_LIEU + " VARCHAR(50) NULL, " + COL_NATURE + " VARCHAR(100) NULL, "
            + COL_EFFECTIF + " INT NULL, " + COL_NB_SECOURISTE + " INT NULL, "
            + COL_DATE_OUVERTURE + " VARCHAR(50) NULL, " + COL_DATE_FERMETURE + " INT NULL, "
            + COL_REMARQUES + " TEXT NULL, " + COL_DIMENTIONNEMENT + " VARCHAR(50) NULL);";

    private static final String TABLE_MAIN_COURANTE = "table_main_courante";
    private static final String COL_ID_MC = "id";
    private static final String COL_HEURE_DEBUT = "heure_debut";
    private static final String COL_HEURE_FIN = "heure_fin";
    private static final String COL_PRENOM = "prenom";
    private static final String COL_NOM_MC = "nom";
    private static final String COL_SEXE = "sexe";
    private static final String COL_AGE = "age";
    private static final String COL_MOTIF = "motif";
    private static final String COL_SOINS = "soins";
    private static final String COL_REMARQUES_MC = "remarques";

    private static final String CREATE_BDD_MC = "CREATE TABLE " + TABLE_MAIN_COURANTE + " ("
            + COL_ID_MC + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_HEURE_DEBUT + " VARCHAR(50) NULL, "
            + COL_HEURE_FIN + " VARCHAR(50) NULL, " + COL_PRENOM + " VARCHAR(50) NULL, "
            + COL_NOM_MC + " VARCHAR(50) NOT NULL, " + COL_SEXE + " VARCHAR(50) NULL, "
            + COL_AGE + " INT NULL, " + COL_MOTIF + " VARCHAR(50) NULL, "
            + COL_SOINS + " TEXT NULL, " + COL_REMARQUES_MC + " TEXT NULL);";

    public GestionDpsSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
        db.execSQL(CREATE_BDD_MC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_FICHE_POSTE + ";");
        db.execSQL("DROP TABLE " + TABLE_MAIN_COURANTE + ";");
        onCreate(db);
    }
}
