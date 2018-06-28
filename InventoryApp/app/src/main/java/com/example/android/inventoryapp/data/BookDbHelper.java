package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryapp.data.BookContract.BookEntry;

public class BookDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = BookDbHelper.class.getSimpleName();

    /** Name of the database file */

    private static final String DATABASE_NAME = "books.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */

    public static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link BookDbHelper}
     *
     * @param context of the app
     */

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time
     */

    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains SQL statement to create the books table.

        String SQL_CREATE_BOOKS_TABLE =
                "CREATE TABLE " + BookEntry.TABLE_NAME + " (" +
                        BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        BookEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                        BookEntry.COLUMN_PRICE + " INTEGER NOT NULL DEFAULT 0, " +
                        BookEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0, " +
                        BookEntry.COLUMN_SUPPLIER_NAME + " TEXT, " +
                        BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " INTEGER);";



        // Execute the SQL statement
        db.execSQL(SQL_CREATE_BOOKS_TABLE);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is still at version 1, so there's nothing to be done here.
    }


}
