package com.example.news_project.databaseModel

import android.content.ContentValues
import com.example.news_project.database.CreateDatabase

class FavoritesDao {

    fun addFavorite(db: CreateDatabase,
                    source:String,author:String,
                    title:String,description:String,
                    url:String,urlToImage:String,
                    publishedAt:String, content:String){

        val db=db.writableDatabase

        val values=ContentValues()

        values.put("source",source)
        values.put("author",author)
        values.put("title",title)
        values.put("description",description)
        values.put("url",url)
        values.put("urlToImage",urlToImage)
        values.put("publishedAt",publishedAt)
        values.put("content",content)

        db.insertOrThrow("favorites",null,values)
        db.close()

    }

    fun getFavorites(db:CreateDatabase):ArrayList<Favorites>{

        val favoritesList=ArrayList<Favorites>()

        val db=db.writableDatabase

        val cursor=db.rawQuery("SELECT * FROM favorites",null)

        while(cursor.moveToNext()){

            val favorite=Favorites(cursor.getInt(cursor.getColumnIndex("favorite_no")),cursor.getString(cursor.getColumnIndex("source")),
                cursor.getString(cursor.getColumnIndex("author")),cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("description")),cursor.getString(cursor.getColumnIndex("url")),
                cursor.getString(cursor.getColumnIndex("urlToImage")),cursor.getString(cursor.getColumnIndex("publishedAt")),
                cursor.getString(cursor.getColumnIndex("content")))

            favoritesList.add(favorite)

        }

        return favoritesList

    }


    fun getFavoritesSearhCheck(db:CreateDatabase, keyword:String):ArrayList<Favorites>{

        val favoritesList=ArrayList<Favorites>()

        val db=db.writableDatabase

        val cursor=db.rawQuery("SELECT * FROM favorites WHERE url like '%$keyword%'",null)

        while(cursor.moveToNext()){

            val favorite=Favorites(cursor.getInt(cursor.getColumnIndex("favorite_no")),cursor.getString(cursor.getColumnIndex("source")),
                    cursor.getString(cursor.getColumnIndex("author")),cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("description")),cursor.getString(cursor.getColumnIndex("url")),
                    cursor.getString(cursor.getColumnIndex("urlToImage")),cursor.getString(cursor.getColumnIndex("publishedAt")),
                    cursor.getString(cursor.getColumnIndex("content")))

            favoritesList.add(favorite)

        }

        return favoritesList

    }
}