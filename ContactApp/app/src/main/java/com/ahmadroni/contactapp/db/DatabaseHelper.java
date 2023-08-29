package com.ahmadroni.contactapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ahmadroni.contactapp.db.entity.ContactEntity;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact_db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ContactEntity.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ContactEntity.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    // Insert Data into Database
    public long insertContact(String name, String email, String phone, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ContactEntity.COLUMN_NAME, name);
        values.put(ContactEntity.COLUMN_EMAIL, email);
        values.put(ContactEntity.COLUMN_PHONE, phone);
        values.put(ContactEntity.COLUMN_ADDRESS, address);

        long id = db.insert(ContactEntity.TABLE_NAME, null, values);

        db.close();

        return id;
    }


    // Getting Contact from DataBase
    public ContactEntity getContact(long id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ContactEntity.TABLE_NAME,
                new String[]{
                        ContactEntity.COLUMN_ID,
                        ContactEntity.COLUMN_NAME,
                        ContactEntity.COLUMN_EMAIL,
                        ContactEntity.COLUMN_PHONE,
                        ContactEntity.COLUMN_ADDRESS},
                ContactEntity.COLUMN_ID + "=?",
                new String[]{
                        String.valueOf(id)
                },
                null,
                null,
                null,
                null);

        if (cursor !=null)
            cursor.moveToFirst();

        ContactEntity contact = new ContactEntity(
                cursor.getInt(cursor.getColumnIndexOrThrow(ContactEntity.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContactEntity.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContactEntity.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContactEntity.COLUMN_PHONE)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContactEntity.COLUMN_ADDRESS))
        );

        cursor.close();
        return contact;

    }

    // Getting all Contacts
    public ArrayList<ContactEntity> getAllContacts(){
        ArrayList<ContactEntity> contacts = new ArrayList<>();


        String selectQuery = "SELECT * FROM " +ContactEntity.TABLE_NAME + " ORDER BY "+
                ContactEntity.COLUMN_ID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                ContactEntity contact = new ContactEntity();
                contact.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ContactEntity.COLUMN_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntity.COLUMN_NAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntity.COLUMN_EMAIL)));
                contact.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntity.COLUMN_PHONE)));
                contact.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntity.COLUMN_ADDRESS)));

                contacts.add(contact);

            }while(cursor.moveToNext());
        }

        db.close();

        return contacts;
    }



    public int updateContact(ContactEntity contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContactEntity.COLUMN_NAME, contact.getName());
        values.put(ContactEntity.COLUMN_EMAIL, contact.getEmail());

        return db.update(ContactEntity.TABLE_NAME, values,ContactEntity.COLUMN_ID+ " = ? ",
                new String[]{String.valueOf(contact.getId())});

    }

    public void deleteContact(ContactEntity contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ContactEntity.TABLE_NAME, ContactEntity.COLUMN_ID + " = ?",
                new String[]{String.valueOf(contact.getId())}
        );
        db.close();
    }
}
