package i4a.epsi.gestiondps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class FichePosteSQLite
 */
public class FichePosteSQLite extends SQLiteOpenHelper {

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
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOM + " INTEGER NOT NULL, "
            + COL_DATE_DEBUT + " VARCHAR(50) NOT NULL, " + COL_DATE_FIN + " VARCHAR(50) NOT NULL, "
            + COL_LIEU + " VARCHAR(50) NOT NULL, " + COL_NATURE + " VARCHAR(100) NOT NULL, "
            + COL_EFFECTIF + " INT NOT NULL, " + COL_NB_SECOURISTE + " INT NOT NULL, "
            + COL_DATE_OUVERTURE + " VARCHAR(50) NOT NULL, " + COL_DATE_FERMETURE + " INT NOT NULL, "
            + COL_REMARQUES + " TEXT NOT NULL, " + COL_DIMENTIONNEMENT + " VARCHAR(50) NOT NULL);";

    public FichePosteSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_FICHE_POSTE + ";");
        onCreate(db);
    }
}
