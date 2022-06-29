package com.example.testlemonde.domain

import com.example.testlemonde.presentation.news.NewsState
import com.example.testlemonde.presentation.news.NewsUi

sealed class NewsDomainState

data class NewsDomainSuccess(val news: List<News>) : NewsDomainState()
object NewsDomainError: NewsDomainState()

data class News(val title: String)