package com.example.news_project

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_project.adapter.Adapter
import com.example.news_project.adapter.FavoriteAdapter
import com.example.news_project.database.CreateDatabase
import com.example.news_project.databaseModel.Favorites
import com.example.news_project.databaseModel.FavoritesDao
import com.example.news_project.model.NewsModel
import com.example.news_project.service.INewsAPI
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentFavorites:Fragment()
{



    private lateinit var newsList:ArrayList<Favorites>
    private lateinit var adapter: FavoriteAdapter




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView=inflater.inflate(R.layout.fragment_favorites,container,false)

        rootView.fragment_recyclerFavorites.setHasFixedSize(true)
        rootView.fragment_recyclerFavorites.layoutManager= LinearLayoutManager(activity)

        activity?.let { getAllFavorites(it,rootView.fragment_recyclerFavorites) }

        return rootView
    }




    fun getAllFavorites(context: Context, recy: RecyclerView){
        val db= CreateDatabase(context)
        val favoriteList= FavoritesDao().getFavorites(db)

        adapter = FavoriteAdapter(context,favoriteList)

        recy.adapter = adapter
    }


}





















