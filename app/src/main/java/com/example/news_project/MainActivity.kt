package com.example.news_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_project.adapter.Adapter
import com.example.news_project.client.ApiUtils
import com.example.news_project.model.NewsModel
import com.example.news_project.service.INewsAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(){

  /*  private var newsModel:ArrayList<NewsModel>?=null
    private var recyViewAdapter:RecyViewAdapter?=null*/

    private lateinit var newsList:ArrayList<NewsModel>
    private lateinit var adapter:Adapter
    private lateinit var fdi:INewsAPI

    private lateinit var tempFragment: Fragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     /*   recyNews.setHasFixedSize(true)
        recyNews.layoutManager= LinearLayoutManager(this)//Listenin görünümü


        fdi = ApiUtils.getINewsApi()*/

        supportFragmentManager.beginTransaction().add(R.id.frame_recyclerView,FragmentNews()).commit()

       bottom_navigation.setOnItemSelectedListener { menuItem ->

            if(menuItem.itemId==R.id.action_news){
                tempFragment=FragmentNews()
            }

            if(menuItem.itemId==R.id.action_favorites){
                tempFragment=FragmentFavorites()
            }
           supportFragmentManager.beginTransaction().replace(R.id.frame_recyclerView,tempFragment).commit()

            true

        }

//getNews()
/*
        srcNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrEmpty()){
                    getNews(query)
                   // Toast.makeText(applicationContext,query,Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(applicationContext,"",Toast.LENGTH_LONG).show()
                }
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })*/

    }


/*
fun getNews(search:String){

    fdi.getNews(search,"1","45bdca3f409241dab160fbecc8a944fd").enqueue(object :Callback<NewsModel>{
        override fun onFailure(call: Call<NewsModel>, t: Throwable) {
            Log.e("Messaj",t.toString())
        }

        override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
            if(response != null){
                val liste = response.body()!!.articleModels

                adapter = Adapter(applicationContext,liste)

                recyNews.adapter = adapter
            }
        }
    })
}
*/



}


