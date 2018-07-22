package com.example.android.inventoryappstage2.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Rosen on 6/27/18
 */

public final class BookContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor
    private BookContract() {

    }

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website. A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */

    public static final String CONTENT_AUTHORITY = "com.example.android.inventoryappstage2";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's
     * content://com.example.android.inventoryappstage2/books/ is a valid path for
     * looking at book data. content://com.example.android.com.inventoryappstage2/staff will fail
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_BOOKS = "books";


    public static final class BookEntry implements BaseColumns {

        /**
         * Name of the database table for books
         */

        public static final String TABLE_NAME = "books";

        /**
         * Unique ID number for the pet (only for use in the database table)
         */

        public static final String _ID = BaseColumns._ID;

        /**
         * Name of the product
         *
         * Type: TEXT
         */

        public static final String COLUMN_PRODUCT_NAME = "product_name";

        /**
         * Price of the product
         *
         * Type: INTEGER
         */

        public static final String COLUMN_PRICE = "price";

        /**
         * Quantity of the product
         *
         * Type: INTEGER
         */
        public static final String COLUMN_QUANTITY = "quantity";

        /**
         * Supplier name
         *
         * Type: TEXT
         */

        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";

        /**
         * Supplier phone number
         *
         * Type: INTEGER
         */

        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

        /** The content URI to access the book data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        /**
         * The MIME type of the  {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +
                        PATH_BOOKS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +
                        PATH_BOOKS;

    }

}
