package com.demo.multilanguage;

public class Note {

    public static final String TABLE_NAME_Save_Offlinedata = "Offlinedata";
    public static final String TABLE_NAME_placetable = "placetable";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_Place_ID = "place_id";
    public static final String COLUMN_Placename = "Placename";
    public static final String COLUMN_State = "State";
    public static final String COLUMN_Country = "Country";
    public static final String COLUMN_Latitude = "Latitude";
    public static final String COLUMN_Longitude = "Longitude";
    public static final String COLUMN_Timezone1 = "Timezone1";
    public static final String COLUMN_Timezone2 = "Timezone2";
    public static final String COLUMN_Timezone3 = "Timezone3";

    public static final String CREATE_placetable =
            "CREATE TABLE " + TABLE_NAME_placetable + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_Place_ID + " TEXT,"
                    + COLUMN_Placename + " TEXT,"
                    + COLUMN_State + " TEXT,"
                    + COLUMN_Country + " TEXT,"
                    + COLUMN_Latitude + " TEXT,"
                    + COLUMN_Longitude + " TEXT,"
                    + COLUMN_Timezone1 + " TEXT,"
                    + COLUMN_Timezone2 + " TEXT,"
                    + COLUMN_Timezone3 + " TEXT"

                    + ")";

    /*public static final String COLUMN_ID = "id";
    public static final String COLUMN_DateOffline= "Date";
    public static final String COLUMN_SunriseOfDay= "SunriseOfDay";
    public static final String COLUMN_SunsetOfDay= "SunsetOfDay";
    public static final String COLUMN_DayChoghadiyaLenth= "DayChoghadiyaLenth";
    public static final String COLUMN_NightChoghadiyaLenth= "NightChoghadiyaLenth";
    public static final String COLUMN_CreateDate= "CreateDate";
    public static final String COLUMN_CityName= "CityName";
    public static final String COLUMN_TimeZone= "TimeZone";
    public static final String COLUMN_Latitude= "Latitude";
    public static final String COLUMN_longitudeD= "longitude";

    public static final String CREATE_Offlinedata =
            "CREATE TABLE " + TABLE_NAME_Save_Offlinedata + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DateOffline + " TEXT,"
                    + COLUMN_SunriseOfDay + " TEXT,"
                    + COLUMN_SunsetOfDay + " TEXT,"
                    + COLUMN_DayChoghadiyaLenth + " TEXT,"
                    + COLUMN_NightChoghadiyaLenth + " TEXT,"
                    + COLUMN_CreateDate + " TEXT,"
                    + COLUMN_CityName + " TEXT,"
                    + COLUMN_TimeZone + " TEXT,"
                    + COLUMN_Latitude + " TEXT,"
                    + COLUMN_longitudeD + " TEXT"

                    + ")";*/

}
