package com.example.news_project.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CreateDatabase(context: Context):SQLiteOpenHelper(context,"News",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE favorites(favorite_no INTEGER PRIMARY KEY AUTOINCREMENT " +
                ",source TEXT, author TEXT, title TEXT, description TEXT, url TEXT, urlToImage TEXT, publishedAt TEXT, content TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS favorites")
        onCreate(db)
    }
}