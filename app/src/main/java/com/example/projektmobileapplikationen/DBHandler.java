package com.example.projektmobileapplikationen;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
        db.execSQL("CREATE TABLE " + TABLE_RK_NAME + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_BEZ + " TEXT, " + KEY_STARTTIME + " TEXT, " + KEY_ENDTIME + " TEXT, " + KEY_STARTDATE + " TEXT, " + KEY_ENDDATE + " TEXT, " + KEY_PAYMENT + " REAL" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RK_NAME);
        // Creating tables again
        onCreate(db);
    }

    /**
     * Adds a Reise to the DB
     * @param reise
     */
    public String addTrip(Reise reise){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Match Values from ergebnis to columns
        values.put(KEY_BEZ, reise.getBezeichnung()); //Bezeichnung == textTitel; Entscheide dich für etwas FLO!
        values.put(KEY_STARTTIME, reise.getStartZeit());
        values.put(KEY_ENDTIME, reise.getEndZeit());
        values.put(KEY_STARTDATE, reise.getStartDatum());
        values.put(KEY_ENDDATE, reise.getEndDatum());
        values.put(KEY_PAYMENT, reise.getAuszahlung());


        try {
            db.insertOrThrow(TABLE_RK_NAME, null, values);
        } catch (SQLException e) {
            return e.getMessage();
        }

        return "passt";


    }

    /**
     * Returns a list of all entries in the DB
     * @return
     */
    public List<Reise> getAllTrips(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RK_NAME, null);
        List<Reise> results = new ArrayList<Reise>();

        //get all results
        if(cursor.moveToFirst()){
            do{
                //create trip from db
                Reise reise = new Reise(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6));
                //add trip to list
                results.add(reise);
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
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_RK_NAME);
        return numRows;
    }

    /**
     * Retrieves one ergebnis from DB using its unique ID
     * @param id
     * @return Ergebnis or null, if there is no matching ergebnis (only the case if messed with the ID or the Ergebnis has not yet been saved)
     */
    public Reise getTripByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_RK_NAME, new String[] {KEY_BEZ, KEY_STARTTIME, KEY_ENDTIME, KEY_STARTDATE, KEY_ENDDATE, KEY_PAYMENT}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Reise reise = new Reise(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6));

        return reise;
    }

    /**
     * Updates one Entry. Returns numbers of successful updates (should be one)
     * Takes the updated reise as parameter.
     * Do not mess with the ID parameter of ergebnis!
     * @param reise
     * @return
     */
    public int updateTrip(Reise reise) {
        SQLiteDatabase db = this.getWritableDatabase();

        //creating Values to change
        ContentValues values = new ContentValues();
        values.put(KEY_BEZ, reise.getBezeichnung()); //Bezeichnung == textTitel; Entscheide dich für etwas FLO!
        values.put(KEY_STARTTIME, reise.getStartZeit());
        values.put(KEY_ENDTIME, reise.getEndZeit());
        values.put(KEY_STARTDATE, reise.getStartDatum());
        values.put(KEY_ENDDATE, reise.getEndDatum());
        values.put(KEY_PAYMENT, reise.getAuszahlung());

        // actual update
        return db.update(TABLE_RK_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(reise.getId())});
    }

    /**
     * Deletes the given Reise from the DB
     * @param reise
     */
    public void deleteTrip(Reise reise) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RK_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(reise.getId()) });
        db.close();
    }
}
