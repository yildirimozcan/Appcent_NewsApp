package com.example.news_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.activity_news_source.*

class ActivityNewsSource : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_source)


        var getLink=intent.getStringExtra("link")

        webViewSource.webViewClient= WebViewClient()
        webViewSource.getSettings().setJavaScriptEnabled(true);
        getLink?.let {
            webViewSource.loadUrl(it);
        }

   imgSourceBack.setOnClickListener {
       finish()
   }

    }
}