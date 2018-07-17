package com.example.android.inventoryappstage2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

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
    public void bindView(View view, Context context, Cursor cursor) {
        // Find the individual views that we want to modify in the list item layout
        TextView productNameTextView = view.findViewById(R.id.product_name);
        TextView priceTextView = view.findViewById(R.id.price);
        TextView quantityTextView = view.findViewById(R.id.quantity);

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

    }

}
