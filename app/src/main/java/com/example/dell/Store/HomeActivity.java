package com.example.dell.Store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.addproduct)
    Button addproduct;
    @BindView(R.id.productlist)
    ListView productlist;
    private DatabaseReference mDatabase;
    ArrayList<Product> arrayOfproducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("products");

        // My top posts by number of stars
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayOfproducts = new ArrayList<Product>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Product product = postSnapshot.getValue(Product.class);
                    arrayOfproducts.add(product);
                }
                // Create the adapter to convert the array to views
                ProductAdapter adapter = new ProductAdapter(HomeActivity.this, arrayOfproducts);
                // Attach the adapter to a ListView
                productlist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void addProduct(View view) {
        Intent addproductintent = new Intent(this, AddProductActivity.class);
        startActivity(addproductintent);
    }
}
