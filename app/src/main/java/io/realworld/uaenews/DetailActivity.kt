package io.realworld.uaenews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
   val url = intent.getStringExtra("URL")
        if (url !=null){
       // binding webview
         detailwebview.settings.javaScriptEnabled= true
            detailwebview.webViewClient= object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility=View.GONE
                    detailwebview.visibility = View.VISIBLE
                }
            }

            detailwebview.loadUrl(url)
        }



    }
}