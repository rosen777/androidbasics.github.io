package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventoryapp.data.BookDbHelper;
import com.example.android.inventoryapp.data.BookContract.BookEntry;

public class EditorActivity extends AppCompatActivity {

    /** EditText field to enter the product's name */
    private EditText mProductNameEditText;

    /** EditText field to enter the price */
    private EditText mPriceEditText;

    /** EditText field to enter the quantity */
    private EditText mQuantityEditText;

    /** EditText field to enter the supplier name */
    private EditText mSupplierNameEditText;

    /** EditText field to enter the suppler's price */
    private EditText mSupplierPhoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input form
        mProductNameEditText = findViewById(R.id.edit_product_name);
        mPriceEditText = findViewById(R.id.edit_price);
        mQuantityEditText = findViewById(R.id.edit_quantity);
        mSupplierNameEditText = findViewById(R.id.edit_supplier_name);
        mSupplierPhoneNumberEditText = findViewById(R.id.edit_supplier_phone_number);

    }

   private void insertBook() {
        // Read from input fields
       // Use trim to eliminate landing or trailing white space
       String productNameString = mProductNameEditText.getText().toString().trim();
       String priceString = mPriceEditText.getText().toString().trim();
       Integer price = Integer.parseInt(priceString);
       String quantityString = mQuantityEditText.getText().toString().trim();
       Integer quantity = Integer.parseInt(quantityString);
       String supplierNameString = mSupplierNameEditText.getText().toString().trim();
       String supplierPhoneNumberString = mSupplierPhoneNumberEditText.getText().toString().trim();
       Integer phoneNumber = Integer.parseInt(supplierPhoneNumberString);

       // Create database helper
       BookDbHelper mDbHelper = new BookDbHelper(this);

       // Get the database in write mode
       SQLiteDatabase db = mDbHelper.getReadableDatabase();

       // Create a ConstantValues object where column names are the keys,
       // and book attributes from the editor are the values.
       ContentValues values = new ContentValues();
       values.put(BookEntry.COLUMN_PRODUCT_NAME, productNameString);
       values.put(BookEntry.COLUMN_PRICE, price);
       values.put(BookEntry.COLUMN_QUANTITY, quantity);
       values.put(BookEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
       values.put(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER, phoneNumber);

       // Insert a new row for book in the database, returning the ID of that new row.
       long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);

       // Show a toast message depending on whether or not the insert was successful
       if(newRowId == -1) {
           //If the new row ID is -1, then there was an error with the insertion.
           Toast.makeText(this, getString(R.string.error_save), Toast.LENGTH_SHORT).show();

           Log.v("EditorActivity", getString(R.string.new_row) + newRowId);
       } else {
           // Otherwise, the insertion was successful and we can display a toast with the row ID.
           Toast.makeText(this, getString(R.string.book_saved) + newRowId, Toast.LENGTH_SHORT).show();
       }
   }



   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
       // This adds menu items to the app bar.
       getMenuInflater().inflate(R.menu.menu_editor, menu);
       return true;
   }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
       switch(item.getItemId()) {
           // Respond to a click on the "Save" menu option
           case R.id.action_save:
               // Save book to database
               if(mProductNameEditText.getText().toString().trim().equals("") || mPriceEditText.getText().toString().trim().equals("") || mQuantityEditText.equals("")
                       || mSupplierNameEditText.getText().toString().trim().equals("") || mSupplierPhoneNumberEditText.getText().toString().trim().equals("")){
                   mProductNameEditText.setError(getString(R.string.required_product_name));
                   mProductNameEditText.setHint(getString(R.string.enter_product_name));
                   mPriceEditText.setError(getString(R.string.required_price));
                   mPriceEditText.setHint(getString(R.string.enter_price));
                   mQuantityEditText.setError(getString(R.string.required_quantity));
                   mQuantityEditText.setHint(getString(R.string.enter_quality));
                   mSupplierNameEditText.setError(getString(R.string.required_supplier_name));
                   mSupplierNameEditText.setHint(getString(R.string.enter_supplier_name));
                   mSupplierPhoneNumberEditText.setError(getString(R.string.required_phone_number));
                   mSupplierPhoneNumberEditText.setHint(R.string.enter_phone_number);
                   return false;
               } else {
                   insertBook();
                   //Exit activity
                   finish();
                   return true;
               }
               // Respond to a click on the "Up" arrow button in the app bar
           case android.R.id.home:
               //Navigate back to parent activity (InventoryActivity)
               NavUtils.navigateUpFromSameTask(this);
               return true;
       }
       return super.onOptionsItemSelected(item);
   }

}
