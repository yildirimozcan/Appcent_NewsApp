package com.example.news_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.news_project.database.CreateDatabase
import com.example.news_project.databaseModel.Favorites
import com.example.news_project.databaseModel.FavoritesDao
import com.example.news_project.model.ArticleModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_detail.*
import java.io.Serializable
import java.lang.Exception
import java.util.*

class ActivityNewsDetail : AppCompatActivity() {

    private lateinit var getNews:ArticleModel
    private lateinit var getFavoriteNews:Favorites


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val check=intent.getIntExtra("check",0)

        if(check==0){
            getNews = intent.getSerializableExtra("newsObject") as ArticleModel

            newsDetail(getNews)
        }else{
            getFavoriteNews = intent.getSerializableExtra("favoriteList") as Favorites
            favoriteDetail(getFavoriteNews)
        }

    }

    fun newSource(objects: Serializable){
        val intent=Intent(this@ActivityNewsDetail, ActivityNewsSource::class.java)
        if(objects is ArticleModel){
            intent.putExtra("link",objects.url)

        }else if(objects is Favorites){
            intent.putExtra("link",objects.url)
        }

        startActivity(intent)
    }


   /* fun addingFavorite(){
        val db=CreateDatabase(this)

        val favoritesDao=FavoritesDao()

        try{
            favoritesDao.addFavorite(db,getNews.sourceModel.name,getNews.author,
                getNews.title,getNews.description,
                getNews.url,getNews.urlToImage,
                getNews.publishedAt,getNews.content)

            Toast.makeText(applicationContext,"Favorilere eklendi",Toast.LENGTH_LONG).show()

            //////
           // getAllFavorites()
            //////

        }catch (e:Exception){
            Log.e("Mesaj",e.toString())
        }

    }*/

    fun addingFavorite(keyword:String){
        val db=CreateDatabase(this)
        val favoritesCheck=FavoritesDao().getFavoritesSearhCheck(db,keyword)
        val favoritesDao=FavoritesDao()

        if(favoritesCheck.isEmpty()){
            try{
                favoritesDao.addFavorite(db,getNews.sourceModel.name,getNews.author,
                        getNews.title,getNews.description,
                        getNews.url,getNews.urlToImage,
                        getNews.publishedAt,getNews.content)

                Toast.makeText(applicationContext,"Favorilere eklendi",Toast.LENGTH_LONG).show()

            }catch (e:Exception){
                Log.e("Mesaj",e.toString())
            }
        }else{
            Toast.makeText(applicationContext,"Daha önce eklenmiş",Toast.LENGTH_LONG).show()
        }

    }


    fun newsDetail(model:ArticleModel){

        txtDetailTitle.text=model.title
        txtDetailAuthor.text=model.author
        txtDetailContent.text=model.content


        val date = model.publishedAt.substringBeforeLast("T")
        txtDetailDate.text = date

        val url = "${model.urlToImage}"

        Picasso.get().load(url).into(imgNewsImage)


        btnNewsSource.setOnClickListener {
            newSource(model)
        }

        imgAddFavorite.setOnClickListener {
            addingFavorite(model.url)

        }

        imgShareLink.setOnClickListener {
            shareLink(model.url)
        }

        imgBack.setOnClickListener {
            finish()
        }
    }

    fun favoriteDetail(model:Favorites){

        txtDetailTitle.text=model.title
        txtDetailAuthor.text=model.author
        txtDetailContent.text=model.content

        val date = model.publishedAt.substringBeforeLast("T")
        txtDetailDate.text = date

        val url = "${model.urlToImage}"

        Picasso.get().load(url).into(imgNewsImage)


        btnNewsSource.setOnClickListener {
            newSource(model)
        }
        imgShareLink.setOnClickListener {
            shareLink(model.url)
        }
        imgBack.setOnClickListener {
            finish()
        }
        imgAddFavorite.setOnClickListener {
            addingFavorite(model.url)
        }

    }

    fun shareLink(linkUrl:String){
        val intent=Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT,"Sharing URL")
        intent.putExtra(Intent.EXTRA_TEXT,linkUrl)
        startActivity(Intent.createChooser(intent,"Share News URL"))

    }
}















