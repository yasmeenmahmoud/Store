package com.example.dell.Store;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProductActivity extends AppCompatActivity {

    @BindView(R.id.addname_edt)
    TextInputEditText addnameEdt;
    @BindView(R.id.addquentity_edt)
    TextInputEditText addquentityEdt;
    @BindView(R.id.addprice_edt)
    TextInputEditText addpriceEdt;
    @BindView(R.id.add)
    Button add;
    String name, price, quentity;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void add(View view) {
        name = addnameEdt.getText().toString();
        price = addpriceEdt.getText().toString();
        quentity = addquentityEdt.getText().toString();
        if (name.isEmpty() || name.equals("")) {
            addnameEdt.setError("Error ");
            return;

        }
        if (price.isEmpty() || price.equals("")) {
            addpriceEdt.setError("Error ");
            return;

        }
        if (quentity.isEmpty() || quentity.equals("")) {
            addquentityEdt.setError("Error");
            return;
        }
        Product product = new Product(name, price, quentity);
        //get new id with any product added
        String key = mDatabase.child("products").push().getKey();
        //set id to my db to edit it if i need
        product.setId(key);
        //write to firebase db
        mDatabase.child("products").child(key).setValue(product);
        addnameEdt.setText("");
        addpriceEdt.setText("");
        addquentityEdt.setText("");
        Toast.makeText(this, "Product Added Successfully ^^", Toast.LENGTH_SHORT).show();
    }
}
