package com.mustofa.englishessence;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.mustofa.englishessence.adapter.FbpostAdapter;
import com.mustofa.englishessence.adapter.ItemsAdapter;
import com.mustofa.englishessence.interfaces.ItemClickInterface;
import com.mustofa.englishessence.model.ItemsModel;
import com.mustofa.englishessence.model.Posts;
import com.mustofa.englishessence.services.AllApi;
import com.mustofa.englishessence.services.RetrofitClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class FbGroup extends AppCompatActivity {
    public static final String TAG = FbGroup.class.getSimpleName();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView recyclerView;
    LayoutManager layoutManager;
    FbpostAdapter fbpostAdapter;
    AllApi allApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setup retrofit for api call
        Retrofit retrofitClass = RetrofitClass.getInstance();
        allApi = retrofitClass.create(AllApi.class);

        //initialize recyclerview
        initRecyclerview();



    }


    private void initRecyclerview() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_posts);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        fbpostAdapter = new FbpostAdapter(new ItemClickInterface() {
            @Override
            public void onPositionClicked(int position, int id) {

            }

        });
        //fetch data from server
        fetchData();






    }

    private void fetchData() {
        compositeDisposable.add(allApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Posts>>() {
                    @Override
                    public void accept(ArrayList<Posts> posts) throws Exception {
                        Log.d(TAG, "accept: post items " + posts.size());
                        displayData(posts);
                    }
                }));
    }

    private void displayData(ArrayList<Posts> posts) {
        recyclerView.setAdapter(fbpostAdapter);
        fbpostAdapter.productList(posts);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

}
