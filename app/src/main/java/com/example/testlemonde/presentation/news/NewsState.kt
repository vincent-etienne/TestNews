package com.example.testlemonde.presentation.news

sealed class NewsState

data class NewsSuccess(val newsUi: List<NewsUi>) : NewsState()
object NewsLoading : NewsState()
object NewsError: NewsState()