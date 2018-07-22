package com.example.android.inventoryappstage2;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryappstage2.data.BookContract.BookEntry;
/**
 * {@link BookCursorAdapter} is an adapter for a list of grid view
 * tha uses a {@link Cursor} of book data as its data source/ This adapter knows
 * how to create list items for each row of book data in the {@link Cursor}.
 */
public class BookCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link BookCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */

    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param view      Existing view, returned earlier by newView() method
     * @param context   app context
     * @param cursor    The cursor from which to get the data. The cursor is already moved to the
     *                  correct row.
     */

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find the individual views that we want to modify in the list item layout
        TextView productNameTextView = view.findViewById(R.id.product_name);
        TextView priceTextView = view.findViewById(R.id.price);
        TextView quantityTextView = view.findViewById(R.id.quantity);
        ImageButton sellButton = view.findViewById(R.id.sell_button);

        // Find the columns of the book attributes that we're interested in
        int productNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);

        //Read the book attributes from the Cursor the current book
        String productName = cursor.getString(productNameColumnIndex);
        String price = cursor.getString(priceColumnIndex);
        String quantity = cursor.getString(quantityColumnIndex);

        // Update the TextViews with the attributes for the current book
        productNameTextView.setText(productName);
        priceTextView.setText(price);
        quantityTextView.setText(quantity);

        // Get the current quantity and make into an integer
        String currentQuantityString = cursor.getString(quantityColumnIndex);
        final int currentQuantity = Integer.valueOf(currentQuantityString);
        // Get the rows from the table with the ID
        final int productId = cursor.getInt(cursor.getColumnIndex(BookEntry._ID));

        //Set up the decrement on the sell button
        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set up if the current quantity is positive (currentQuantity > 0)
                if(currentQuantity > 0) {

                    // Decrease the quantity by 1
                    int newQuantity = currentQuantity - 1;

                    // Get the URI with the append of the ID for the row
                    Uri quantityUri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, productId);

                    // Get the current value for quantity and update them with the new decreased quantity
                    ContentValues values = new ContentValues();
                    values.put(BookEntry.COLUMN_QUANTITY, newQuantity);
                    context.getContentResolver().update(quantityUri, values, null, null);
                }

                // Add a Toast message stating that the quantity for this book ran out of stock
                else {
                    Toast.makeText(context, "Sorry, this book is out of stock! " +
                            "\n Grab another good read.", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}
