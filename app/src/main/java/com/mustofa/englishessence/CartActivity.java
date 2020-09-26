package com.mustofa.englishessence;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.mustofa.englishessence.adapter.CartItemsAdapter;
import com.mustofa.englishessence.interfaces.ItemClickInterface;
import com.mustofa.englishessence.model.ItemsModel;
import com.mustofa.englishessence.model.TinyDB;

import java.util.ArrayList;

import ru.nikartm.support.ImageBadgeView;

public class CartActivity extends AppCompatActivity {
    public static final String TAG = CartActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CartItemsAdapter itemsAdapter;
    private TinyDB tinyDB;
    private ArrayList<String> cartItem = new ArrayList<>();
    private ArrayList<ItemsModel> carts = new ArrayList<>();
    private ImageBadgeView cartview;
    private View checkoutbt;
    private TextView total;
    private int totalPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartlayout);
        checkoutbt = findViewById(R.id.chekoutbt);
        total = findViewById(R.id.total);

        tinyDB = new TinyDB(this);



        cartItem = tinyDB.getListString("cartItems");


        for (String item:cartItem
        ) {
            ItemsModel itemsModel = new Gson().fromJson(item,ItemsModel.class);
            carts.add(itemsModel);
            Log.d(TAG, "onCreate: items in cart " + new Gson().toJson(itemsModel));

        }



        initRecyclerview();
        updateTotal();


        checkoutbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast toast = Toast.makeText(CartActivity.this, "This button will lead to next checkout screen that can create order" +
                       "for being time shorten i did not implement it", Toast.LENGTH_LONG);
               toast.show();

            }
        });



    }

    private void updateTotal() {
        int totalP=0;
        for (ItemsModel item :carts
             ) {
            if(item.getProductPrice() !=null)
            {
                totalP = totalP + item.getProductPrice();
                Log.d(TAG, "getTotal: total price " + item.getProductPrice());
            }


        }

        total.setText(String.format("%s %d","$:",totalP));
    }

    private void initRecyclerview() {

        recyclerView = (RecyclerView) findViewById(R.id.cart_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        itemsAdapter = new CartItemsAdapter(new ItemClickInterface() {
            @Override
            public void onPositionClicked(int position, int id) {

                ItemsModel item = carts.get(position);
                String itemString = new Gson().toJson(item);
                Log.d(TAG, "onPositionClicked: item is " + itemString);
                carts.remove(item);
                cartItem.remove(itemString);
                tinyDB.putListString("cartItems",cartItem);
                itemsAdapter.notifyItemRemoved(position);
                updateTotal();

            }

        });
        recyclerView.setAdapter(itemsAdapter);
        itemsAdapter.productList(carts);
        itemsAdapter.notifyDataSetChanged();
    }
}
