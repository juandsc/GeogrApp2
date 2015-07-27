package info.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AreasContract {
    DbHelper dbHelper;

    //Version de la base de datos
    public static final int DB_VERSION = 1;

    //Nombre de la base de datos
    public static final String DB_NAME = "Areas";

    //Nombre de las Tablas
    public static final String GEOGRAFIC_AREA = "geograficArea";
    public static final String COMPASS_MEASURE = "compassMeasure";
    public static final String ROCK_QUALITY = "rockQuality";
    public static final String PLAN = "plan";
    public static final String DISTANCE = "distance";

    //Nombre de columnas comunes
    public static final String ID = "id";
    public static final String AREA = "area";
    public static final String CREATED_AT = "created_at";
    public static final String NAME = "nombre";
    public static final String DESCRIPTION = "descripcion";

    //Tabla COMPASS_MEASURE - nombre de columnas
    public static final String ORIENTATION = "orientation";
    public static final String LEVEL = "level";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";

    //Tabla ROCK_QUALITY - nombre de columnas
    public static final String Q1 = "q1";
    public static final String Q2 = "q2";
    public static final String QUALITY = "quality";

    //Tabla MAP - nombre de columnas
    public static final String DRAWING = "drawing";

    //Tabla DISTANCE - nombre de columnas
    public static final String PUNTO1 = "punto1";
    public static final String PUNTO2 = "punto2";

    //Sentencia creacion tabla GEOGRAFIC_AREA
    public static final String CREATE_TABLE_GEOGRAFIC_AREA = "CREATE TABLE "+GEOGRAFIC_AREA+" ("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME +" TEXT NOT NULL, "+ DESCRIPTION +" TEXT, "+CREATED_AT+
            " DATETIME NOT NULL)";

    //Sentencia creacion tabla COMPASS_MEASURE
    public static final String CREATE_TABLE_COMPASS_MEASURE = "CREATE TABLE "+COMPASS_MEASURE+" ("+
            ID+" INTEGER PRIMARY KEY, "+NAME+" TEXT NOT NULL, "+DESCRIPTION+" TEXT, "+CREATED_AT+
            " DATETIME NOT NULL, "+ORIENTATION+" FLOAT, "+LEVEL+" FLOAT, "+LATITUDE+" DOUBLE, "+
            LONGITUDE+" DOUBLE, "+AREA+" INTEGER NOT NULL, FOREIGN KEY ("+AREA+") REFERENCES "+
            GEOGRAFIC_AREA+" ("+ID+") ON DELETE CASCADE)";

    //Sentencia creacion tabla ROCK-QUALITY
    public static final String CREATE_TABLE_ROCK_QUALITY = "CREATE TABLE "+ROCK_QUALITY+" ("+
            ID+" INTEGER PRIMARY KEY, "+NAME+" TEXT NOT NULL, "+DESCRIPTION+" TEXT, "+CREATED_AT+
            " DATETIME NOT NULL, "+Q1+ " FLOAT, "+Q2+" FLOAT, "+QUALITY+" TEXT, "+AREA+
            " INTEGER, FOREIGN KEY ("+AREA+") REFERENCES "+GEOGRAFIC_AREA+"("+ID+") ON DELETE CASCADE)";

    //Constructor
    public AreasContract(Context context){
        dbHelper = new DbHelper(context);
    }

    //Metodo para insertar una nueva fila en la tabla GEOGRAFIC_AREA
    public void createGeograficArea(GeograficArea geograficArea){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(ID, geograficArea.getId());
        values.put(NAME, geograficArea.getName());
        values.put(DESCRIPTION, geograficArea.getDescription());
        values.put(CREATED_AT, geograficArea.getCreated_at());

        db.insertWithOnConflict(GEOGRAFIC_AREA, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    //Método para retornar un elemento de la tabla GEOGRAFIC_AREA
    public GeograficArea getGeograficArea(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        GeograficArea geograficArea = new GeograficArea();

        String selectQuery = "SELECT * FROM "+GEOGRAFIC_AREA+" WHERE "+ID+" = "+id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        geograficArea.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        geograficArea.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        geograficArea.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        geograficArea.setCreated_at(cursor.getString(cursor.getColumnIndex(CREATED_AT)));

        return geograficArea;
    }

    public GeograficArea getLastGeograficArea(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        GeograficArea geograficArea = new GeograficArea();

        String selectQuery = "SELECT * FROM "+GEOGRAFIC_AREA+" ORDER BY "+ID+" DESC LIMIT 1" ;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null){
            cursor.moveToFirst();

        }
        int indice = cursor.getCount();
        Log.d("Valor de Cursor: ", String.valueOf(indice));
        if(cursor.getCount() > 0){
            geograficArea.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            geograficArea.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            geograficArea.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
            geograficArea.setCreated_at(cursor.getString(cursor.getColumnIndex(CREATED_AT)));

            return geograficArea;
        }
        else{
            return null;
        }
    }

    //Método para retornar toda la tabla GEOGRAFIC_AREA List<GeograficArea> Cursor
    public  Cursor getAllGeograficArea(){
        List<GeograficArea> geograficAreas = new ArrayList<GeograficArea>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT rowid _id, * FROM "+GEOGRAFIC_AREA;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                GeograficArea geograficArea = new GeograficArea();
                geograficArea.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                geograficArea.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                geograficArea.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                geograficArea.setCreated_at(cursor.getString(cursor.getColumnIndex(CREATED_AT)));

                geograficAreas.add(geograficArea);
            }while (cursor.moveToNext());
        }

        return cursor;
    }

    //Metodo para actualizar una fila de la tabla GEOGRAFIC_AREA
    public void updateGeograficArea(GeograficArea geograficArea){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, geograficArea.getName());
        values.put(DESCRIPTION, geograficArea.getDescription());

        db.update(GEOGRAFIC_AREA, values, ID+" = ?", new String[]{String.valueOf(geograficArea.getId())});
    }

    //Metodo para borrar una fila de la tabla GEOGRAFIC_AREA
    public void deleteGeograficArea(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(GEOGRAFIC_AREA, ID+" = ?", new String[]{String.valueOf(id)});
    }

    //Metodo para insertar una nueva fila en la tabla COMPASS_MEASURE
    public void createCompassMeasure(CompassMeasure compassMeasure){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, compassMeasure.getName());
        values.put(DESCRIPTION, compassMeasure.getDescription());
        values.put(CREATED_AT, compassMeasure.getCreated_At());
        values.put(AREA, compassMeasure.getGeograficArea());
        db.insertWithOnConflict(COMPASS_MEASURE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    //Método para retornar un elemento de la tabla COMPASS_MEASURE
    public CompassMeasure getCompassMeasure(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        CompassMeasure compassMeasure = new CompassMeasure();

        String selectQuery = "SELECT * FROM "+COMPASS_MEASURE+" WHERE "+ID+" = "+id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        compassMeasure.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        compassMeasure.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        compassMeasure.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        compassMeasure.setCreated_At(cursor.getString(cursor.getColumnIndex(CREATED_AT)));
        compassMeasure.setLevel(cursor.getFloat(cursor.getColumnIndex(LEVEL)));
        compassMeasure.setLatitude(cursor.getFloat(cursor.getColumnIndex(LATITUDE)));
        compassMeasure.setLongitude(cursor.getFloat(cursor.getColumnIndex(LONGITUDE)));
        compassMeasure.setOrientation(cursor.getFloat(cursor.getColumnIndex(ORIENTATION)));
        compassMeasure.setGeograficArea(cursor.getInt(cursor.getColumnIndex(AREA)));

        return compassMeasure;
    }

    //Metodo para obtener el ultimo elemento ingresado a la tabla COMPASS_MEASURE
    public CompassMeasure getLastCompassMeasure(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        CompassMeasure compassMeasure = new CompassMeasure();

        String selectQuery = "SELECT * FROM "+COMPASS_MEASURE+" ORDER BY "+ID+" DESC LIMIT 1" ;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        compassMeasure.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        compassMeasure.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        compassMeasure.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        compassMeasure.setCreated_At(cursor.getString(cursor.getColumnIndex(CREATED_AT)));
        compassMeasure.setLevel(cursor.getFloat(cursor.getColumnIndex(LEVEL)));
        compassMeasure.setLatitude(cursor.getFloat(cursor.getColumnIndex(LATITUDE)));
        compassMeasure.setLongitude(cursor.getFloat(cursor.getColumnIndex(LONGITUDE)));
        compassMeasure.setOrientation(cursor.getFloat(cursor.getColumnIndex(ORIENTATION)));
        compassMeasure.setGeograficArea(cursor.getInt(cursor.getColumnIndex(AREA)));

        return compassMeasure;
    }

    //Metodo para retornas las medidas de brujula de un area en especifico
    public Cursor getCompassMeasureByArea(int idArea){
        List<CompassMeasure> compassMeasures = new ArrayList<CompassMeasure>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT rowid _id,* FROM "+COMPASS_MEASURE+" WHERE "+AREA+" = "+idArea;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                CompassMeasure compassMeasure = new CompassMeasure();
                compassMeasure.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                compassMeasure.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                compassMeasure.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                compassMeasure.setCreated_At(cursor.getString(cursor.getColumnIndex(CREATED_AT)));
                compassMeasure.setLevel(cursor.getFloat(cursor.getColumnIndex(LEVEL)));
                compassMeasure.setLatitude(cursor.getFloat(cursor.getColumnIndex(LATITUDE)));
                compassMeasure.setLongitude(cursor.getFloat(cursor.getColumnIndex(LONGITUDE)));
                compassMeasure.setOrientation(cursor.getFloat(cursor.getColumnIndex(ORIENTATION)));
                compassMeasure.setGeograficArea(cursor.getInt(cursor.getColumnIndex(AREA)));

                compassMeasures.add(compassMeasure);
            }while (cursor.moveToNext());
        }

        return cursor;
    }

    public int getCountCompassMeasureByeArea(int idArea){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        CompassMeasure compassMeasure = new CompassMeasure();

        String selectQuery = "SELECT COUNT(*) FROM "+COMPASS_MEASURE+" WHERE "+AREA+" = "+idArea;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        int numCompassMeasures = cursor.getInt(0);
        return numCompassMeasures;
    }

    //Método para retornar toda la tabla COMPASS_MEASURE List<CompassMeasure>
    public Cursor getAllCompassMeasure(){
        List<CompassMeasure> compassMeasures = new ArrayList<CompassMeasure>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT rowid _id, * FROM "+COMPASS_MEASURE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                CompassMeasure compassMeasure = new CompassMeasure();
                compassMeasure.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                compassMeasure.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                compassMeasure.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                compassMeasure.setCreated_At(cursor.getString(cursor.getColumnIndex(CREATED_AT)));
                compassMeasure.setLevel(cursor.getFloat(cursor.getColumnIndex(LEVEL)));
                compassMeasure.setLatitude(cursor.getFloat(cursor.getColumnIndex(LATITUDE)));
                compassMeasure.setLongitude(cursor.getFloat(cursor.getColumnIndex(LONGITUDE)));
                compassMeasure.setOrientation(cursor.getFloat(cursor.getColumnIndex(ORIENTATION)));
                compassMeasure.setGeograficArea(cursor.getInt(cursor.getColumnIndex(AREA)));

                compassMeasures.add(compassMeasure);
            }while (cursor.moveToNext());
        }

        return cursor;
    }

    //Metodo para actualizar una fila de la tabla COMPASS_MEASURE
    public void updateCompassMeasure(int id, float orientation, float level, double latitude, double longitude){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ORIENTATION, orientation);
        values.put(LEVEL, level);
        values.put(LATITUDE, latitude);
        values.put(LONGITUDE, longitude);

        db.update(COMPASS_MEASURE, values, ID+" = ?", new String[]{String.valueOf(id)});
    }

    //Metodo para borrar una fila de la tabla COMPASS_MEASURE
    public void deleteCompassMeasure(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(COMPASS_MEASURE, ID+" = ?", new String[]{String.valueOf(id)});
    }

    //Metodo para insertar una nueva fila en la tabla ROCK_QUALITY
    public void createRockQuality(RockQuality rockQuality){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, rockQuality.getName());
        values.put(DESCRIPTION, rockQuality.getDescription());
        values.put(CREATED_AT, rockQuality.getCreated_At());
        values.put(AREA, rockQuality.getGeograficArea());
        db.insertWithOnConflict(ROCK_QUALITY, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    //Método para retornar un elemento de la tabla ROCK_QUALITY
    public RockQuality getRockQuality(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        RockQuality rockQuality = new RockQuality();

        String selectQuery = "SELECT * FROM "+ROCK_QUALITY+" WHERE "+ID+" = "+id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        rockQuality.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        rockQuality.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        rockQuality.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        rockQuality.setCreated_At(cursor.getString(cursor.getColumnIndex(CREATED_AT)));
        rockQuality.setQ1(cursor.getFloat(cursor.getColumnIndex(Q1)));
        rockQuality.setQ2(cursor.getFloat(cursor.getColumnIndex(Q2)));
        rockQuality.setQuality(cursor.getString(cursor.getColumnIndex(QUALITY)));
        rockQuality.setGeograficArea(cursor.getInt(cursor.getColumnIndex(AREA)));

        return rockQuality;
    }

    //Metodo para retornar las medidas de calidad de roca de un area en especifico
    public Cursor getRockQualityByArea(int idArea){
        List<RockQuality> rockQualities = new ArrayList<RockQuality>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT rowid _id,* FROM "+ROCK_QUALITY+" WHERE "+AREA+" = "+idArea;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                RockQuality rockQuality = new RockQuality();
                rockQuality.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                rockQuality.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                rockQuality.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                rockQuality.setCreated_At(cursor.getString(cursor.getColumnIndex(CREATED_AT)));
                rockQuality.setQ1(cursor.getFloat(cursor.getColumnIndex(Q1)));
                rockQuality.setQ2(cursor.getFloat(cursor.getColumnIndex(Q2)));
                rockQuality.setQuality(cursor.getString(cursor.getColumnIndex(QUALITY)));
                rockQuality.setGeograficArea(cursor.getInt(cursor.getColumnIndex(AREA)));

                rockQualities.add(rockQuality);
            }while (cursor.moveToNext());
        }

        return cursor;
    }

    public int getCountRockQualityByArea(int idArea){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        CompassMeasure compassMeasure = new CompassMeasure();

        String selectQuery = "SELECT COUNT(*) FROM "+ROCK_QUALITY+" WHERE "+AREA+" = "+idArea;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        int numQuality = cursor.getInt(0);
        return numQuality;
    }

    //Método para retornar un elemento de la tabla ROCK_QUALITY
    public RockQuality getLastRockQuality(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        RockQuality rockQuality = new RockQuality();

        String selectQuery = "SELECT * FROM "+ROCK_QUALITY+" ORDER BY "+ID+" DESC LIMIT 1" ;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        rockQuality.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        rockQuality.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        rockQuality.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        rockQuality.setCreated_At(cursor.getString(cursor.getColumnIndex(CREATED_AT)));
        rockQuality.setQ1(cursor.getFloat(cursor.getColumnIndex(Q1)));
        rockQuality.setQ2(cursor.getFloat(cursor.getColumnIndex(Q2)));
        rockQuality.setQuality(cursor.getString(cursor.getColumnIndex(QUALITY)));
        rockQuality.setGeograficArea(cursor.getInt(cursor.getColumnIndex(AREA)));

        return rockQuality;
    }

    //Método para retornar toda la tabla ROCK_QUALITY
    public List<RockQuality> getAllRockQuality(){
        List<RockQuality> rockQualities = new ArrayList<RockQuality>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+AreasContract.ROCK_QUALITY;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                RockQuality rockQuality = new RockQuality();
                rockQuality.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                rockQuality.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                rockQuality.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                rockQuality.setCreated_At(cursor.getString(cursor.getColumnIndex(CREATED_AT)));
                rockQuality.setQ1(cursor.getFloat(cursor.getColumnIndex(Q1)));
                rockQuality.setQ2(cursor.getFloat(cursor.getColumnIndex(Q2)));
                rockQuality.setQuality(cursor.getString(cursor.getColumnIndex(QUALITY)));
                rockQuality.setGeograficArea(cursor.getInt(cursor.getColumnIndex(AREA)));

                rockQualities.add(rockQuality);
            }while (cursor.moveToNext());
        }

        return rockQualities;
    }

    //Metodo para actualizar una fila de la tabla ROCK_QUALITY
    public void updateRockQuality(int id, float q1, float q2, String quality){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Q1, q1);
        values.put(Q2, q2);
        values.put(QUALITY, quality);

        db.update(ROCK_QUALITY, values, ID+" = ?", new String[]{String.valueOf(id)});
    }

    //Metodo para borrar una fila de la tabla ROCK_QUALITY
    public void deleteRockQuality(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(ROCK_QUALITY, ID+" = ?", new String[]{String.valueOf(id)});
    }

}
