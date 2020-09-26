package com.mustofa.englishessence.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mustofa.englishessence.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {
    private static Retrofit retrofitInstance;
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit getInstance() {
        if (retrofitInstance == null)


            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return retrofitInstance;
    }


}
