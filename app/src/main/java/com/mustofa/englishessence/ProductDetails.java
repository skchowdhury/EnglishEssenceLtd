package com.mustofa.englishessence;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.mustofa.englishessence.model.ItemsModel;
import com.mustofa.englishessence.model.TinyDB;

import java.util.ArrayList;

public class ProductDetails extends AppCompatActivity {

    public static final String TAG = ProductDetails.class.getSimpleName();
    private TextView item_name;
    private ArrayList<String> cartItem = new ArrayList<>();
    private ArrayList<ItemsModel> carts = new ArrayList<>();
    TinyDB tinyDB;
    String itemString;
    ItemsModel itemsModel;
    View addtoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        item_name = findViewById(R.id.item_name);
        addtoCart = findViewById(R.id.addtocartlayout);
        tinyDB = new TinyDB(this);
        cartItem = tinyDB.getListString("cartItems");

        if (getIntent() != null) {
            itemString = getIntent().getStringExtra("item");
            itemsModel = new Gson().fromJson(itemString, ItemsModel.class);
            item_name.setText(String.format("%s  Price:%d", itemsModel.getProductName(), itemsModel.getProductPrice()));
        }

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetails.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                cartItem.add(itemString);
                tinyDB.putListString("cartItems", cartItem);
                startActivity(new Intent(ProductDetails.this,MainActivity.class));
            }
        });


    }

}
