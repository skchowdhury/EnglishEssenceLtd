package com.mustofa.englishessence.services;

import com.mustofa.englishessence.model.Posts;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AllApi {

    //get posts from server
    @GET("posts")
    Observable<ArrayList<Posts>> getPosts();
}
