package com.example.news_project.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsModel(
    @SerializedName("status")
    @Expose
     var status:String,
@SerializedName("totalResults")
@Expose
 var totalResults:Int,
@SerializedName("articles")
@Expose
 var articleModels:List<ArticleModel>
):Serializable {
}