package com.example.news_project.service

import com.example.news_project.model.NewsModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface INewsAPI {

    //https://newsapi.org/v2/everything?q=besiktas&page=1&apiKey=90f78b5e459f4557a6d285161db89387





/*
    @GET("everything")
    fun getNews(@Query("q") query: String, @Query("page") page:String,
                @Query("apiKey") apiKey:String):Call<NewsModel>*/


    @GET("everything")
    fun getNews(@Query("q") query: String, @Query("page") page:String,
                @Query("apiKey") apiKey:String):Call<NewsModel>



/*
    @GET("everything?q=tesla&page=1&apiKey=45bdca3f409241dab160fbecc8a944fd")
    fun getNews():Call<NewsModel>*/
}