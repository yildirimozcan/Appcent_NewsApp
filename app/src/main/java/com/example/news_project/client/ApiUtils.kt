package com.example.news_project.client

import com.example.news_project.service.INewsAPI

class ApiUtils {

    companion object{
        val BASE_URL="https://newsapi.org/v2/"

        fun getINewsApi():INewsAPI{
            return RetrofitClient.getClient(BASE_URL).create(INewsAPI::class.java)

        }
    }
}