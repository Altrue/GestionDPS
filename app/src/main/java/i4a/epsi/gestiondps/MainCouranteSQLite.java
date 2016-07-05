package i4a.epsi.gestiondps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Class MainCouranteSQLite
 */
public class MainCouranteSQLite extends SQLiteOpenHelper {

    private static final String TABLE_MAIN_COURANTE = "table_main_courante";
    private static final String COL_ID = "id";
    private static final String COL_HEURE_DEBUT = "heure_debut";
    private static final String COL_HEURE_FIN = "heure_fin";
    private static final String COL_PRENOM = "prenom";
    private static final String COL_NOM = "nom";
    private static final String COL_SEXE = "sexe";
    private static final String COL_AGE = "age";
    private static final String COL_MOTIF = "motif";
    private static final String COL_SOINS = "soins";
    private static final String COL_REMARQUES = "remarques";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_MAIN_COURANTE + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_HEURE_DEBUT + " VARCHAR(50) NOT NULL, "
            + COL_HEURE_FIN + " VARCHAR(50) NOT NULL, " + COL_PRENOM + " VARCHAR(50) NOT NULL, "
            + COL_NOM + " VARCHAR(50) NOT NULL, " + COL_SEXE + " VARCHAR(50) NOT NULL, "
            + COL_AGE + " INT NOT NULL, " + COL_MOTIF + " VARCHAR(50) NOT NULL, "
            + COL_SOINS + " TEXT NOT NULL, " + COL_REMARQUES + " TEXT NOT NULL);";

    public MainCouranteSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_MAIN_COURANTE + ";");
        onCreate(db);
    }
}
