package com.example.testlemonde.data

import retrofit2.Response
import retrofit2.http.GET

interface NewsApi{
    @GET("/ws/8/mobile/www/android-phone/en_continu/index.json")
    suspend fun getNews() : Response<NewsResponse>
}