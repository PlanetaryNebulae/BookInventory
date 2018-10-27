package com.example.android.bookinventory;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bookinventory.data.BookContract.BookEntry;
import com.example.android.bookinventory.data.BookDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText bProductNameEditText;

    private EditText bProductPriceEditText;

    private EditText bQuantityEditText;

    private EditText bSupplierNameEditText;

    private EditText bSupplierNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        bProductNameEditText = (EditText) findViewById(R.id.product_name);
        bProductPriceEditText = (EditText) findViewById(R.id.product_price);
        bQuantityEditText = (EditText) findViewById(R.id.quantity_default);
        bSupplierNameEditText = (EditText) findViewById(R.id.supplier_name);
        bSupplierNumberEditText = (EditText) findViewById(R.id.supplier_phone_number);
    }

    private void insertBook() {

        String productNameString = bProductNameEditText.getText().toString().trim();
        String productPriceString = bProductPriceEditText.getText().toString().trim();
        String quantityString = bQuantityEditText.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);
        String supplierNameString = bSupplierNameEditText.getText().toString().trim();
        String supplierNumberString = bSupplierNumberEditText.getText().toString().trim();

        BookDbHelper bDbHelper = new BookDbHelper(this);
        SQLiteDatabase db = bDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_PRODUCT_NAME, productNameString);
        values.put(BookEntry.COLUMN_PRODUCT_PRICE, productPriceString);
        values.put(BookEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(BookEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        values.put(BookEntry.COLUMN_SUPPLIER_NUMBER, supplierNumberString);

        long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error saving book.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book saved with id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertBook();
                finish();
                return true;

            case R.id.action_delete:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
