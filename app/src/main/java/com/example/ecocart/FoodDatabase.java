package com.example.ecocart;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class FoodDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FoodDB.db";
    public static final String TABLE_NAME = "Food";
    public static final String COLUMN_CO2 = "CO2_emissions";
    public static final String COLUMN_TYPE = "FoodType";
    public static final String COLUMN_NAME = "FoodName";

    public FoodDatabase(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_TYPE +
                "INTEGER PRIMARYKEY," + COLUMN_CO2 + "INTEGER," + COLUMN_NAME + "TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(Food food) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, Food.getType());
        values.put(COLUMN_CO2, Food.getCarbonDioxide());
        values.put(COLUMN_NAME, Food.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Food findHandler(String foodItem) {
        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_NAME + " = " + "'" + foodItem + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Food food = new Food();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            Food.setType(cursor.getString(1));
            Food.setCarbonDioxide(Integer.parseInt(cursor.getString(0)));
            Food.setName(cursor.getString(1));
            cursor.close();
        } else {
            food = null;
        }
        db.close();
        return food;
    }

    //public boolean updateHandler(int ID, String name) {}

}