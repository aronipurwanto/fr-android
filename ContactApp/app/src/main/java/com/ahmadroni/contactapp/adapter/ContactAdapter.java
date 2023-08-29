package com.ahmadroni.contactapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadroni.contactapp.MainActivity;
import com.ahmadroni.contactapp.R;
import com.ahmadroni.contactapp.db.entity.ContactEntity;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ContactEntity> contactsList;
    private MainActivity mainActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView email;
        public TextView phone;
        public TextView address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.item_name);
            this.email = itemView.findViewById(R.id.item_email);
            this.phone = itemView.findViewById(R.id.item_phone);
            this.address = itemView.findViewById(R.id.item_address);
        }
    }

    public ContactAdapter(Context context, ArrayList<ContactEntity> contacts, MainActivity mainActivity){
        this.context = context;
        this.contactsList = contacts;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.contact_list_item,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int positions) {
        final ContactEntity contact = contactsList.get(positions);

        holder.name.setText(contact.getName());
        holder.email.setText(contact.getEmail());
        holder.phone.setText(contact.getPhone());
        holder.address.setText(contact.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.addAndEditContacts(true,contact,positions);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}
