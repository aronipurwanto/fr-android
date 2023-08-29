package com.ahmadroni.contactapp.db.entity;

public class ContactEntity {
    // 1- Constants for Database
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "contact_id";
    public static final String COLUMN_NAME = "contact_name";
    public static final String COLUMN_EMAIL = "contact_email";
    public static final String COLUMN_PHONE = "contact_phone";
    public static final String COLUMN_ADDRESS = "address";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_PHONE + " TEXT,"
                    + COLUMN_ADDRESS + " TEXT"
                    + ")";

    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public ContactEntity(){}

    public ContactEntity(int id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
