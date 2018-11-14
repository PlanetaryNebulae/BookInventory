package com.example.android.bookinventory;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.bookinventory.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter {

    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(final View view, Context context, Cursor cursor) {

        //Individual views to modify.
        TextView productNameTextView = (TextView) view.findViewById(R.id.product_name);
        TextView priceTextView = (TextView) view.findViewById(R.id.product_price);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity_default);

        //Columns of product attributes.
        int productNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_QUANTITY);

        //Reads product attributes from the cursor for the current product.
        String productName = cursor.getString(productNameColumnIndex);
        String price = cursor.getString(priceColumnIndex);
        final String quantity = cursor.getString(quantityColumnIndex);

        //Updates the TextView with the attributes for the current pet.
        productNameTextView.setText(productName);
        priceTextView.setText(price);
        quantityTextView.setText(quantity);

        //When sale button is pressed, it reduces quantity by 1.
        Button saleButton = view.findViewById(R.id.sale_button);
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int updatedQuantity = Integer.parseInt(quantity);
                TextView quantityTextView = (TextView) view.findViewById(R.id.quantity_default);

                if (updatedQuantity > 0) {

                    updatedQuantity = updatedQuantity - 1;
                    quantityTextView.setText(Integer.toString(updatedQuantity));

                }
            }
        });
    }
}
