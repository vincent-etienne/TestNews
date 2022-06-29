package com.example.testlemonde.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testlemonde.data.NewsDataSource
import com.example.testlemonde.domain.NewsDomainError
import com.example.testlemonde.domain.NewsDomainSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val dataSource = NewsDataSource()

    private val _newsState : MutableLiveData<NewsState> = MutableLiveData()
    val newsState : LiveData<NewsState>
    get() = _newsState

    init {
        _newsState.postValue(NewsLoading)
        viewModelScope.launch(Dispatchers.IO) {
            when(val domainState = dataSource.getNews()){
                NewsDomainError -> _newsState.postValue(NewsError)
                is NewsDomainSuccess -> _newsState.postValue(NewsSuccess(
                    newsUi = domainState.news.map { NewsUi(it.title) }
                ))
            }
        }
    }
}