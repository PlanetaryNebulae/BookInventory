package com.example.android.bookinventory.data;

import android.provider.BaseColumns;

public final class BookContract {

    private BookContract() {
    }

    public static final class BookEntry implements BaseColumns {

        //Name of the table.
        public static final String TABLE_NAME = "books";

        //Column id.
        public static final String _ID = BaseColumns._ID;

        //Column names.
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_NUMBER = "supplier_phone_number";

    }
}
