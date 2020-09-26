package com.mustofa.englishessence;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.mustofa.englishessence.adapter.ItemsAdapter;
import com.mustofa.englishessence.interfaces.ItemClickInterface;
import com.mustofa.englishessence.model.ItemsModel;
import com.mustofa.englishessence.model.TinyDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.nikartm.support.ImageBadgeView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ItemsAdapter itemsAdapter;
    private TinyDB tinyDB;
    private ArrayList<String> cartItem = new ArrayList<>();
    private ArrayList<ItemsModel> carts = new ArrayList<>();
    private ImageBadgeView cartview;
    private ImageView fbgroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tinyDB = new TinyDB(this);
        cartview = findViewById(R.id.cart);
        fbgroup = findViewById(R.id.fbgroup);

        //setup recyclerview
        initRecyclerview();

        //get saved cart items
        cartItem = tinyDB.getListString("cartItems");

        //setup badge icon for cart size
        cartview.setBadgeValue(cartItem.size());
        cartview.setRoundBadge(true);

        for (String item:cartItem
             ) {
            ItemsModel itemsModel = new Gson().fromJson(item,ItemsModel.class);
            carts.add(itemsModel);
            Log.d(TAG, "onCreate: items in cart " + new Gson().toJson(itemsModel));
        }

        cartview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });

        fbgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FbGroup.class));
            }
        });

    }

    private void initRecyclerview() {
        final ArrayList<ItemsModel> items = generateProducts();
        recyclerView = (RecyclerView) findViewById(R.id.product_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        itemsAdapter = new ItemsAdapter(new ItemClickInterface() {
            @Override
            public void onPositionClicked(int position, int id) {
                if(id ==0) {
                    Toast.makeText(MainActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                    ItemsModel item = items.get(position);
                    String itemString = new Gson().toJson(item);
                    Log.d(TAG, "onPositionClicked: item is " + itemString);

                    cartItem.add(itemString);
                    tinyDB.putListString("cartItems", cartItem);
                    cartview.setBadgeValue(cartItem.size());
                }
                else
                {
                    ItemsModel item = items.get(position);
                    String itemString = new Gson().toJson(item);
                    Intent intent = new Intent(MainActivity.this,ProductDetails.class);
                    intent.putExtra("item",itemString);
                    startActivity(intent);
                }
            }

        });
        recyclerView.setAdapter(itemsAdapter);
        itemsAdapter.productList(items);
        itemsAdapter.notifyDataSetChanged();
    }

    private ArrayList<ItemsModel> generateProducts() {
        ArrayList<ItemsModel> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            items.add(new ItemsModel(i,"Product item " + i,i+10));
        }
        return items;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
