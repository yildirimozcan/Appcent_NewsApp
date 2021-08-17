package com.example.news_project.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.news_project.ActivityNewsDetail
import com.example.news_project.R
import com.example.news_project.model.ArticleModel
import com.example.news_project.model.NewsModel
import com.squareup.picasso.Picasso

class Adapter(private val mContext: Context, private val newsList:List<ArticleModel>): RecyclerView.Adapter<Adapter.CardTasarimTutucu>()

{


    inner class CardTasarimTutucu(tasarim: View) : RecyclerView.ViewHolder(tasarim){
        var newsCard: CardView
        var txtTitle: TextView
        var txtDescription: TextView
        var txtDate: TextView
        var txtSource: TextView
        var imgNews: ImageView

        init {
            newsCard = tasarim.findViewById(R.id.rowCardView)
            txtTitle = tasarim.findViewById(R.id.txtTitle)
            txtDescription = tasarim.findViewById(R.id.txtDescription)
            txtDate = tasarim.findViewById(R.id.txtDate)
            txtSource = tasarim.findViewById(R.id.txtSource)
            imgNews = tasarim.findViewById(R.id.imgImage)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_design,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val news = newsList.get(position)

        holder.txtTitle.text = news.title
        holder.txtDescription.text = news.description

        val date = news.publishedAt.substringBeforeLast("T")
        holder.txtDate.text = date


        holder.txtSource.text = news.sourceModel.name

        //val url = "http://kasimadalan.pe.hu/filmler/resimler/${news.urlToImage}"
        val url = "${news.urlToImage}"

        Picasso.get().load(url).into(holder.imgNews)

        /* holder.film_card.setOnClickListener {

            val intent = Intent(mContext,DetayActivity::class.java)
            intent.putExtra("filmNesne",film)
            mContext.startActivity(intent)

        }*/

        holder.newsCard.setOnClickListener {

            val intent = Intent(mContext, ActivityNewsDetail::class.java)
            intent.putExtra("newsObject",news)
            intent.putExtra("check",0)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

}