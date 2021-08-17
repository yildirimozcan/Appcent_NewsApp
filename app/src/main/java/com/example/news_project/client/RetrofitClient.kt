package com.example.news_project.client

import com.example.news_project.service.INewsAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {




    companion object{
        var client = OkHttpClient
                .Builder()
                .build()

        fun getClient(baseUrl:String):Retrofit{
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                .build()
        }
    }
}