package com.demo.multilanguage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "choghadiyaplace_db";
    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Note.CREATE_placetable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME_placetable);

    }


    public long InserPlaceSave(String place_id,
                                     String Placename,
                                     String State,
                                     String Country,
                                     String Latitude,
                                     String Longitude,
                                     String Timezone1,
                                     String Timezone2,
                                     String Timezone3)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_Place_ID, place_id);
        values.put(Note.COLUMN_Placename, Placename);
        values.put(Note.COLUMN_State, State);
        values.put(Note.COLUMN_Country, Country);
        values.put(Note.COLUMN_Latitude, Latitude);
        values.put(Note.COLUMN_Longitude, Longitude);
        values.put(Note.COLUMN_Timezone1, Timezone1);
        values.put(Note.COLUMN_Timezone2, Timezone2);
        values.put(Note.COLUMN_Timezone3, Timezone3);



        long id = db.insert(Note.TABLE_NAME_placetable, null, values);
        return id;

    }


    public void deletedata(){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Note.TABLE_NAME_placetable,null,null);

    }


    /*public long InserDataOfflineSave(String DateOffline,
                                     String SunriseOfDay,
                                     String SunsetOfDay,
                                     String DayChoghadiyaLenth,
                                     String NightChoghadiyaLenth,
                                     String CreateDate,
                                     String CityName,
                                     String TimeZone,
                                     String Latitude,
                                     String longitude)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_DateOffline, DateOffline);
        values.put(Note.COLUMN_SunriseOfDay, SunriseOfDay);
        values.put(Note.COLUMN_SunsetOfDay, SunsetOfDay);
        values.put(Note.COLUMN_DayChoghadiyaLenth, DayChoghadiyaLenth);
        values.put(Note.COLUMN_NightChoghadiyaLenth, NightChoghadiyaLenth);
        values.put(Note.COLUMN_CreateDate, CreateDate);
        values.put(Note.COLUMN_CityName, CityName);
        values.put(Note.COLUMN_TimeZone, TimeZone);
        values.put(Note.COLUMN_Latitude, Latitude);
        values.put(Note.COLUMN_longitudeD, longitude);

        long id = db.insert(Note.TABLE_NAME_Save_Offlinedata, null, values);
        return id;

    }

    public List<OfflineData> getofflinedata(String date){
        List<OfflineData> offlineDataList = new ArrayList<OfflineData>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor  cursor = db.query(Note.TABLE_NAME_Save_Offlinedata, null, Note.COLUMN_DateOffline + "='" + date + "'", null, null, null, null);
        //Cursor  cursor = db.query(Note.TABLE_NAME_Save_Offlinedata, null, Note.COLUMN_DateOffline + "='" + date + "' and " + Note.COLUMN_DateOffline + "='" + 0 + "'", null, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                OfflineData offlineData=new OfflineData();
                offlineData.setDateOffline(cursor.getString(1));
                offlineData.setSunriseOfDay(cursor.getString(2));
                offlineData.setSunsetOfDay(cursor.getString(3));
                offlineData.setDayChoghadiyaLenth(cursor.getString(4));
                offlineData.setNightChoghadiyaLenth(cursor.getString(5));
                offlineData.setCreateDate(cursor.getString(6));
                offlineData.setCityName(cursor.getString(7));
                offlineData.setTimeZone(cursor.getString(8));
                offlineData.setLatitude(cursor.getString(9));
                offlineData.setLongitude(cursor.getString(10));

                offlineDataList.add(offlineData);

            } while (cursor.moveToNext());
        }


        return offlineDataList;
    }

    public boolean checkdate(String date,String city){

        boolean check;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor  cursor = db.query(Note.TABLE_NAME_Save_Offlinedata, null, Note.COLUMN_DateOffline + "='" + date + "' and " + Note.COLUMN_CityName + "='" + city + "'", null, null, null, null);

        if (cursor.getCount()<=0){
            check=false;
        }else {
            check=true;
        }

        return check;
    }

    public void deletedata(){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Note.TABLE_NAME_Save_Offlinedata,null,null);

    }*/

}
