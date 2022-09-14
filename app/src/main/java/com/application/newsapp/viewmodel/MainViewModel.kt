package com.application.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.newsapp.model.CommentsResponse
import com.application.newsapp.model.NewsCategoriesResponse
import com.application.newsapp.model.NewsItem
import com.application.newsapp.model.NewsResponse

class MainViewModel : ViewModel() {

    val newsResponse: MutableLiveData<NewsResponse?> by lazy {
        MutableLiveData<NewsResponse?>()
    }

    val newsDetailResponse: MutableLiveData<NewsResponse?> by lazy {
        MutableLiveData<NewsResponse?>()
    }

    val newsCategoriesResponse: MutableLiveData<NewsCategoriesResponse?> by lazy {
        MutableLiveData<NewsCategoriesResponse?>()
    }

    val commentsResponse: MutableLiveData<CommentsResponse?> by lazy {
        MutableLiveData<CommentsResponse?>()
    }

}