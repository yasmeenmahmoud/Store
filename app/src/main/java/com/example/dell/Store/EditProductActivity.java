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

public class EditProductActivity extends AppCompatActivity {
    @BindView(R.id.editname_edt)
    TextInputEditText editnameEdt;
    @BindView(R.id.editquentity_edt)
    TextInputEditText editquentityEdt;
    @BindView(R.id.editprice_edt)
    TextInputEditText editpriceEdt;
    @BindView(R.id.update)
    Button update;
    String prouductname, _productprice, _productquentity, id;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        prouductname = getIntent().getExtras().getString("productname");
        _productprice = getIntent().getExtras().getString("productprice");
        _productquentity = getIntent().getExtras().getString("productquentity");
        id = getIntent().getExtras().getString("productid");

        editnameEdt.setText(prouductname);
        editpriceEdt.setText(_productprice);
        editquentityEdt.setText(_productquentity);
    }

    public void update(View view) {
        String E_name, E_price, E_quentity;
        E_name = editnameEdt.getText().toString();
        E_price = editpriceEdt.getText().toString();
        E_quentity = editquentityEdt.getText().toString();
        Product product = new Product(E_name, E_price, E_quentity);
        product.setId(id);
        mDatabase.child("products").child(id).setValue(product);
        Toast.makeText(this, "data Edited Successfully^^", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}
