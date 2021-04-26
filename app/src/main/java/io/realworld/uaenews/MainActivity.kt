package io.realworld.uaenews

import android.app.ProgressDialog.show
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var mInterstitialAd: InterstitialAd? = null

    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    var pageNum= 1
    var totalResults = -1

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}

        adapter = NewsAdapter(this@MainActivity,articles)
        newsList.adapter = adapter
      //  newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        // corosol implementation to be set in here

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.TOP_TO_BOTTOM)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        // pass out to recycler view
        newsList.layoutManager= layoutManager

        // try to change color
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
                Log.d(TAG, "First visible Item - ${layoutManager.getFirstVisibleItemPosition()}" )
                Log.d(TAG, "TOTAL COUNT- ${layoutManager.itemCount}")
                if (totalResults > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition()
                        >= layoutManager.itemCount -5){
                    //next page
                    pageNum++
                    getNews()
                }

            }

        })
        getNews()
    }
    private fun getNews(){
        Log.d(TAG, "Request sent for $pageNum")
        val news = NewsService.newsInstance.getHeadlines("in",pageNum)
        news.enqueue(object: Callback<News> {


            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Kashsoft","Error in Fetching News",t)

            }
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news !=null){
                    totalResults = news.totalResults
                    Log.d("Kashsoft", news.toString())
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()

                }
            }

        })
    }
}