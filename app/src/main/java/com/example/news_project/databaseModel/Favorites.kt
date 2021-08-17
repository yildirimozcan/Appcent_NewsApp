package com.example.news_project.databaseModel

import java.io.Serializable

data class Favorites(var favorite_no:Int,
                     var source:String,
                     var author:String,
                     var title:String,
                     var description:String,
                     var url:String,
                     var urlToImage:String,
                     var publishedAt:String,
                     var content:String):Serializable {
}