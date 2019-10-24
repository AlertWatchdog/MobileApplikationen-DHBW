package com.example.projektmobileapplikationen;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "reisekosten";
    //RK table name
    private static final String TABLE_RK_NAME = "reisekostenAbrechnungen";
    //RK table column names
    private static final String KEY_ID = "id";
    private static final String KEY_BEZ = "bezeichnung";
    private static final String KEY_STARTTIME = "startzeit";
    private static final String KEY_ENDTIME = "endzeit";
    private static final String KEY_STARTDATE = "startdatum";
    private static final String KEY_ENDDATE = "enddatum";
    private static final String KEY_PAYMENT = "auszahlung";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    /** Creates a new Table when the Database is created. Table columns as seen above under RK Table column names.
     * Date and Time columns use TEXT Datatype. Date and Time HAVE to be ISO8601 strings ("YYYY-MM-DD and HH:MM:SS.SSS").
     */
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_RK_NAME + "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_BEZ + "TEXT," + KEY_STARTTIME + "TEXT," + KEY_ENDTIME + "TEXT," + KEY_STARTDATE + "TEXT," + KEY_ENDDATE + "TEXT," + KEY_PAYMENT + "REAL" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RK_NAME);
        // Creating tables again
        onCreate(db);
    }

    /**
     * Adds a Ergebnis (a.k.a. Businesstrip) to the DB
     * @param ergebnis
     */
    public void addTrip(Ergebnis ergebnis){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Match Values from ergebnis to columns
        values.put(KEY_BEZ, ergebnis.getBezeichnung()); //Bezeichnung == textTitel; Entscheide dich für etwas FLO!
        values.put(KEY_STARTTIME, ergebnis.getStartzeit());
        values.put(KEY_ENDTIME, ergebnis.getEndzeit());
        values.put(KEY_STARTDATE, ergebnis.getStartdatum());
        values.put(KEY_ENDDATE, ergebnis.getEnddatum());
        values.put(KEY_PAYMENT, ergebnis.getAuszahlung());
    }

    /**
     * Returns a list of all entries in the DB
     * @return
     */
    public List<Ergebnis> getAllTrips(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM" + TABLE_RK_NAME, null);
        List<Ergebnis> results = new ArrayList<Ergebnis>();

        //get all results
        if(cursor.moveToFirst()){
            do{
                Ergebnis ergebnis = new Ergebnis();
                ergebnis.setID(cursor.getString(0)); //Ergebnisse, die Gespeichert werden bekommen von DB eine ID zugewiesen
                ergebnis.setBezeichnung(cursor.getString(1));
                ergebnis.setStartzeit(cursor.getString(2));
                ergebnis.setEndzeit(cursor.getString(3));
                ergebnis.setStarrtdatum(cursor.getString(4));
                ergebnis.setEnddatum(cursor.getString(5));
                ergebnis.setAuszahlung(cursor.getDouble(8));

                results.add(ergebnis);
            }while(cursor.moveToNext());
        }

        //return all trips
        return results;
    }

    /**
     * Returns the number of entries in the DB
     * @return int
     */
    public int getTripCount(){
        String countQuery = "SELECT * FROM " + TABLE_RK_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    /**
     * Retrieves one ergebnis from DB using its unique ID
     * @param id
     * @return Ergebnis or null, if there is no matching ergebnis (only the case if messed with the ID or the Ergebnis has not yet been saved)
     */
    public Ergebnis getTripByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_RK_NAME, new String[] {KEY_BEZ, KEY_STARTTIME, KEY_ENDTIME, KEY_STARTDATE, KEY_ENDDATE, KEY_PAYMENT}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Ergebnis ergebnis = new Ergebnis();
        ergebnis.setID(cursor.getString(0)); //Ergebnisse, die Gespeichert werden bekommen von DB eine ID zugewiesen
        ergebnis.setBezeichnung(cursor.getString(1));
        ergebnis.setStartzeit(cursor.getString(2));
        ergebnis.setEndzeit(cursor.getString(3));
        ergebnis.setStarrtdatum(cursor.getString(4));
        ergebnis.setEnddatum(cursor.getString(5));
        ergebnis.setAuszahlung(cursor.getDouble(8));

        return ergebnis;
    }

    /**
     * Updates one Enty. Returns numbers of successful updates (should be one)
     * Takes the updated ergebnis as parameter.
     * Do not mess with the ID parameter of ergebnis!
     * @param ergebnis
     * @return
     */
    public int updateShop(Ergebnis ergebnis) {
        SQLiteDatabase db = this.getWritableDatabase();

        //creating Values to change
        ContentValues values = new ContentValues();
        values.put(KEY_BEZ, ergebnis.getBezeichnung()); //Bezeichnung == textTitel; Entscheide dich für etwas FLO!
        values.put(KEY_STARTTIME, ergebnis.getStartzeit());
        values.put(KEY_ENDTIME, ergebnis.getEndzeit());
        values.put(KEY_STARTDATE, ergebnis.getStartdatum());
        values.put(KEY_ENDDATE, ergebnis.getEnddatum());
        values.put(KEY_PAYMENT, ergebnis.getAuszahlung());

        // actual update
        return db.update(TABLE_RK_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(ergebnis.getID())});
    }

    /**
     * Deletes the given ergebnis from the DB
     * @param ergebnis
     */
    public void deleteTrip(Ergebnis ergebnis) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RK_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(ergebnis.getId()) });
        db.close();
    }
}
