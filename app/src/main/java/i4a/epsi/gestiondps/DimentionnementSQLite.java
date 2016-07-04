package i4a.epsi.gestiondps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class DimentionnementSQLite
 */
public class DimentionnementSQLite extends SQLiteOpenHelper {
    private static final String TABLE_DIMENTIONNEMENT = "table_dimentionnement";
    private static final String COL_ID = "id";
    private static final String COL_LIBELLE = "libelle";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_DIMENTIONNEMENT + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LIBELLE + " VARCHAR(50) NOT NULL;";

    private static final String INSERT_VALUES = "INSERT INTO" + TABLE_DIMENTIONNEMENT + "(" + COL_LIBELLE + ")"
            + "VALUES('DPS-PE');" + "INSERT INTO" + TABLE_DIMENTIONNEMENT + "(" + COL_LIBELLE + ")"
            + "VALUES('DPS-ME');" + "INSERT INTO" + TABLE_DIMENTIONNEMENT + "(" + COL_LIBELLE + ")"
            + "VALUES('DPS-GE');";

    public DimentionnementSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
        db.execSQL(INSERT_VALUES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_DIMENTIONNEMENT + ";");
        onCreate(db);
    }
}
