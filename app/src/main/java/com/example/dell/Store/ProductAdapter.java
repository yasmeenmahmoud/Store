package com.example.dell.Store;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    Context context;
    private DatabaseReference mDatabase;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        super(context, 0, products);
        this.context = context;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("products");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Product product = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product_row, parent, false);
        }
        TextView prouductName = (TextView) convertView.findViewById(R.id.productname);
        TextView prouductprice = (TextView) convertView.findViewById(R.id.productprice);
        TextView prouductquentity = (TextView) convertView.findViewById(R.id.productquentity);
        ImageButton deleteitem = convertView.findViewById(R.id.delete_btn);
        ImageButton edititem = convertView.findViewById(R.id.edit_btn);

        prouductName.setText(product.getName());
        prouductprice.setText("Price: " + product.getPrice());
        prouductquentity.setText("Quentity: " + product.getQuentity());
        edititem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.homecontent, new EditFragment());
//                transaction.addToBackStack(null);
//                transaction.commit();
//                EditProductActivity editProductActivity=(EditProductActivity)getContext();
//                editProductActivity.product=product;
                Intent editproductintent = new Intent(context, EditProductActivity.class);
                editproductintent.putExtra("productname", product.getName());
                editproductintent.putExtra("productprice", product.getPrice());
                editproductintent.putExtra("productquentity", product.getQuentity());
                editproductintent.putExtra("productid", product.getId());

                context.startActivity(editproductintent);
            }
        });
        deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want to delete this product?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mDatabase.child(product.getId()).removeValue();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
        return convertView;
    }
}
