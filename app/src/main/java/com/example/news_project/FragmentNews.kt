package com.example.news_project

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_project.adapter.Adapter
import com.example.news_project.client.ApiUtils
import com.example.news_project.model.ArticleModel
import com.example.news_project.model.NewsModel
import com.example.news_project.service.INewsAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentNews:Fragment() {

    private lateinit var newsList:ArrayList<NewsModel>
    private lateinit var adapter: Adapter
    private  var fdi: INewsAPI=ApiUtils.getINewsApi()

    private var compositeDisposable:CompositeDisposable= CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView=inflater.inflate(R.layout.fragment_news,container,false)

        rootView.fragment_recyNews.setHasFixedSize(true)
        rootView.fragment_recyNews.layoutManager= LinearLayoutManager(activity)//Listenin görünümü

      //  fdi = ApiUtils.getINewsApi()



        rootView.fragment_searchNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrEmpty()){
                    getNews(query,activity!!,rootView.fragment_recyNews)
                    // Toast.makeText(applicationContext,query,Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(activity,"", Toast.LENGTH_LONG).show()
                }
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


        return rootView
    }


  fun getNews(search:String,context:Context,recy:RecyclerView){

        fdi.getNews(search,"1","45bdca3f409241dab160fbecc8a944fd").enqueue(object :
            Callback<NewsModel> {
            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.e("Messaj",t.toString())
            }

            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if(response != null){
                    val liste = response.body()!!.articleModels

                    adapter = Adapter(context,liste)

                    recy.adapter = adapter
                }
            }
        })
    }







}