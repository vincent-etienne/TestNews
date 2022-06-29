package com.example.testlemonde.data

import android.util.Log
import com.example.testlemonde.domain.News
import com.example.testlemonde.domain.NewsDomainState
import com.example.testlemonde.domain.NewsDomainError
import com.example.testlemonde.domain.NewsDomainSuccess
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

class NewsDataSource {
    val okhttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    val newsApi = Retrofit.Builder()
        .baseUrl("https://aec.lemonde.fr")
        .client(okhttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    suspend fun getNews() : NewsDomainState {
        return try {
            val response = newsApi.getNews()
            return if(response.isSuccessful ){
                val news = response.body()?.elements?.mapNotNull {
                    it.titre?.let { title ->
                        News(title)
                    }
                } ?: emptyList()
                if(news.isNotEmpty()){
                    NewsDomainSuccess(news)
                } else {
                    NewsDomainError
                }
            } else {
                NewsDomainError
            }

        } catch (e: Exception) {
            Log.d("Vince", "Vince Exception", e)
            NewsDomainError
        }
    }
}