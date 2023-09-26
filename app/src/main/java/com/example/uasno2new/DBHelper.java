package com.example.uasno2new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uasno2new.Model.ItemHistory;
import com.example.uasno2new.Model.ItemList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    //HISTORY
    public static final String TABLE_NAME_HISTORY = "historys";
    public static final String COL_ID_HISTORY = "id_history";
    public static final String COL_ID_USER_HISTORY = "id_users";
    public static final String COL_ID_ITEM_HISTORY = "id_item";
    public static final String COL_DATE_HISTORY = "date";
    public static final String COL_TIME_HISTORY = "time";


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
        String CREATE_TABLE_HISTORY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_HISTORY + "(" +
                COL_ID_HISTORY+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COL_ID_USER_HISTORY + " INTEGER NOT NULL," +
                COL_ID_ITEM_HISTORY + " INTEGER NOT NULL," +
                COL_DATE_HISTORY + " DATE NOT NULL," +
                COL_TIME_HISTORY+ " TIME NOT NULL)";

        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_ITEM);
        sqLiteDatabase.execSQL(CREATE_TABLE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ITEM);
         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HISTORY);
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

    //Cari item
    public ItemList searchItem(Integer id){
        SQLiteDatabase db = getReadableDatabase();

        // String query = "SELECT * FROM " + TABLE_NAME_ITEM + " WHERE " + KEY_ITEM_ID + "=?";
        String query = "SELECT * FROM " + TABLE_NAME_ITEM + " WHERE " + COL_ID_ITEM + "=" + id;
        // Cursor cursor = db.rawQuery(query, new String[]{id.toString()});
        Cursor cursor = db.rawQuery(query, null);
        ItemList item = null;
        if(cursor.moveToFirst()){
            item = new ItemList(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
        }
        return item;
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

    public long insertHistory(ItemHistory itemHistory) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        //akses item history -> get_Item() (ada di file ItemHistory) -> get_idItem() (ada di file ItemList) buat ambil value dari id itemnya
        values.put(COL_ID_ITEM_HISTORY, itemHistory.get_Item().get_idItem());
        values.put(COL_ID_USER_HISTORY, itemHistory.get_IdUser());
        values.put(COL_DATE_HISTORY, itemHistory.get_Date().toString());
        values.put(COL_TIME_HISTORY, itemHistory.get_Time().toString());

        if(db.insert(TABLE_NAME_HISTORY, null, values) == -1){
            db.close();
            return -1;
        }
        db.close();
        return 1;

    }

    public ArrayList<ItemHistory> get_History(Integer userId){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ItemHistory> arrHistory = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME_HISTORY + " WHERE " + COL_ID_USER_HISTORY + "=?" + " ORDER BY " + COL_ID_HISTORY + " DESC";
        Cursor cursor = db.rawQuery(query, new String[]{userId.toString()});
        if(cursor.moveToFirst()){
            arrHistory.add(new ItemHistory(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    searchItem(cursor.getInt(2)),
                    formatDate(cursor.getString(3)),
                    formatTime(cursor.getString(4))

            ));
            while(cursor.moveToNext()){
                arrHistory.add(new ItemHistory(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        searchItem(cursor.getInt(2)),
                        formatDate(cursor.getString(3)),
                        formatTime(cursor.getString(4))
                ));
            }
        }
        return arrHistory;
    }

    private LocalTime formatTime(String localTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime formattedTime = LocalTime.parse(localTime, formatter);
        return LocalTime.from(formattedTime);
    }

    private LocalDate formatDate(String localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formattedDate = LocalDate.parse(localDate,formatter);
        return LocalDate.from(formattedDate);
    }


}
