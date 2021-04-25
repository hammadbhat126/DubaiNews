package io.realworld.uaenews

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import retrofit2.http.GET
import retrofit2.http.Query


// https://newsapi.org/v2/everything?q=tesla&from=2021-03-25&sortBy=publishedAt&apiKey=b9b5bc2ae00b4eb5a441b36d8192e207

// https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=b9b5bc2ae00b4eb5a441b36d8192e207

const val  BASE_URL= "https://newsapi.org/"
const val  API_KEY = "b9b5bc2ae00b4eb5a441b36d8192e207"
interface NewsInterface{


    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country:String,@Query("page")page:Int) : Call<News>

}

// made singlinton
// made retrofit object
object NewsService{
    val newsInstance : NewsInterface
init {
    val retrofit =Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    newsInstance = retrofit.create(NewsInterface::class.java)
}

}
