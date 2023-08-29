package com.ahmadroni.contactapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadroni.contactapp.adapter.ContactAdapter;
import com.ahmadroni.contactapp.db.DatabaseHelper;
import com.ahmadroni.contactapp.db.entity.ContactEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ContactAdapter contactsAdapter;
    private ArrayList<ContactEntity> contactArrayList  = new ArrayList<>();
    private RecyclerView recyclerView;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Favorite Contacts");

        // RecyclerVIew
        recyclerView = findViewById(R.id.recycler_view_contacts);
        db = new DatabaseHelper(this);

        /*
        contactArrayList.add(new ContactEntity(1,"Roni","ahmadroni@gmail.com","6281234567890","Ciamis"));
        contactArrayList.add(new ContactEntity(2,"Dhika","dhika@gmail.com","6281234567890","Ciamis"));
        contactArrayList.add(new ContactEntity(3,"Dhika","dhika@gmail.com","6281234567890","Ciamis"));
        contactArrayList.add(new ContactEntity(4,"Dhika","dhika@gmail.com","6281234567890","Ciamis"));
        contactArrayList.add(new ContactEntity(5,"Dhika","dhika@gmail.com","6281234567890","Ciamis"));
        */
        contactArrayList.addAll(db.getAllContacts());

        contactsAdapter = new ContactAdapter(this, contactArrayList,MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactsAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAndEditContacts(false, null, -1);
            }
        });
    }

    public void addAndEditContacts(boolean isUpdated, ContactEntity contact, int positions) {
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.contact_add,null);

        AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alerDialogBuilder.setView(view);


        TextView contactTitle = view.findViewById(R.id.new_contact_title);
        final EditText contactName = view.findViewById(R.id.add_name);
        final EditText contactEmail = view.findViewById(R.id.add_email);
        final EditText contactPhone = view.findViewById(R.id.add_phone);
        final EditText contactAddress = view.findViewById(R.id.add_address);

        contactTitle.setText(!isUpdated ? "Add New Contact" : "Edit Contact");

        if (isUpdated && contact != null){
            contactName.setText(contact.getName());
            contactEmail.setText(contact.getEmail());
            contactPhone.setText(contact.getPhone());
            contactAddress.setText(contact.getAddress());
        }

        alerDialogBuilder.setCancelable(false)
                .setPositiveButton(isUpdated ? "Update" : "Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                if (isUpdated){
                                    deleteContactAction(contact, positions);
                                }else{
                                    dialogInterface.cancel();
                                }
                            }
                        }
                );

        final AlertDialog alertDialog = alerDialogBuilder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(contactName.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please Enter a Name", Toast.LENGTH_SHORT).show();

                    return;
                }else{
                    alertDialog.dismiss();
                }

                if (isUpdated && contact != null){
                    updateContactAction(contactName.getText().toString(), contactEmail.getText().toString(),
                            contactPhone.getText().toString(), contactAddress.getText().toString(), positions);

                }else{
                    createContactAction(contactName.getText().toString(), contactEmail.getText().toString(),
                            contactPhone.getText().toString(), contactAddress.getText().toString());

                }

            }
        });
    }

    private void deleteContactAction(ContactEntity contact, int position) {
        contactArrayList.remove(position);
        db.deleteContact(contact);
        contactsAdapter.notifyDataSetChanged();
    }


    private void updateContactAction(String name, String email, String phone, String address, int position){
        ContactEntity contact = contactArrayList.get(position);

        contact.setName(name);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setAddress(address);

        db.updateContact(contact);

        contactArrayList.set(position, contact);
        contactsAdapter.notifyDataSetChanged();
    }


    private void createContactAction(String name, String email, String phone, String address){

        long id = db.insertContact(name, email, phone, address);
        ContactEntity contact = db.getContact(id);

        if (contact != null){
            contactArrayList.add(0, contact);
            contactsAdapter.notifyDataSetChanged();
        }

    }
}