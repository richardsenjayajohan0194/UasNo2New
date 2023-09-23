package com.example.uasno2new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uasno2new.Model.ItemList;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "signup.db";

    //USER
    public static final String TABLE_NAME_USER = "users";
    public static final String COL_ID_USER = "id_users";
    public static final String COL_USERNAME = "username";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";

    //ITEM
    public static final String TABLE_NAME_ITEM = "items";
    public static final String COL_ID_ITEM = "id_item";
    public static final String COL_IMAGE = "image";
    public static final String COL_ITEM_NAME_AND_SIZE = "itemNameAndSize";
    public static final String COL_PRICE = "price";
    private String username;


    public DBHelper(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USER + "(" +
                COL_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COL_USERNAME + " TEXT NOT NULL," +
                COL_EMAIL + " TEXT NOT NULL," +
                COL_PASSWORD + " TEXT NOT NULL)";
        String CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ITEM + "(" +
                COL_ID_ITEM + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COL_IMAGE + " TEXT NOT NULL," +
                COL_ITEM_NAME_AND_SIZE + " TEXT NOT NULL," +
                COL_PRICE + " TEXT NOT NULL)";

        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ITEM);
    }

    //Insert data at SignUp or registration form
    public boolean insetData(String username, String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        long result = myDB.insert(TABLE_NAME_USER, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //Check when user Sgn Up or Register have the same username or not
    public boolean checkUsername(String username) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + TABLE_NAME_USER + " WHERE " + COL_USERNAME + "=?", new String[]{username});
        if (cursor.getCount()>0){
            return true;
        } else {
            return false;
        }
    }

    public User checkUser(String username, String password) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + TABLE_NAME_USER+ " WHERE " + COL_USERNAME + "=? AND "  + COL_PASSWORD + "=?", new String[]{username,password});
        User user = null;
        if (cursor.moveToFirst()) {
            user = new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        }
        cursor.close();
        return user;
    }

    public boolean UpdatePassword(Integer userId, String old_password, String new_password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", new_password);
        return myDB.update(TABLE_NAME_USER, contentValues, COL_ID_USER + "=? AND " + COL_PASSWORD + "=?", new String[]{userId.toString(),old_password}) > 0;
    }

    public boolean DeleteAccount(Integer userId){
        SQLiteDatabase myDB = this.getWritableDatabase();
        return myDB.delete(TABLE_NAME_USER, COL_ID_USER + "=?", new String[]{userId.toString()}) > 0;

    }

    //insert Item
    public long insertItem(ItemList item){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_IMAGE, item.get_imageSource());
        values.put(COL_ITEM_NAME_AND_SIZE, item.get_nameItemAndSize());
        values.put(COL_PRICE, item.get_price());

        if(db.insert(TABLE_NAME_ITEM, null, values) == -1){
            db.close();
            return -1;
        }
        db.close();
        return 1;
    }

    public ArrayList<ItemList> getItems(){
        ArrayList<ItemList> arrItem = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_ITEM;
        Cursor cursor = db.rawQuery(query, null);
        // Integer id-0, String imageUrl-1, String name-2, String size-4, Integer price-3
        if(cursor.moveToFirst()){
            arrItem.add(new ItemList(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            ));
            while(cursor.moveToNext()){
                arrItem.add(new ItemList(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                ));
            }
        }
        return arrItem;
    }
}
