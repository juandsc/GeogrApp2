package info.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jds.dsd.geograpp.areasGeograficas.FragmentListaAreas;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, AreasContract.DB_NAME, null, AreasContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("PRAGMA foreign_keys=ON");
        //creamos las tablas de la base de datos
        String sql = AreasContract.CREATE_TABLE_GEOGRAFIC_AREA;
        db.execSQL(sql);

        sql = AreasContract.CREATE_TABLE_ROCK_QUALITY;
        db.execSQL(sql);

        sql = AreasContract.CREATE_TABLE_COMPASS_MEASURE;
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //actualizacion de las tablas
        db.execSQL("DROP TABLE IF EXIST "+AreasContract.GEOGRAFIC_AREA);
        db.execSQL("DROP TABLE IF EXIST "+AreasContract.COMPASS_MEASURE);
        db.execSQL("DROP TABLE IF EXIST "+AreasContract.ROCK_QUALITY);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }


}
