package com.example.testlemonde.data

data class NewsResponse(
    val elements: List<NewsData>
)

data class NewsData(
    val key: String?,
    val titre: String?
)
