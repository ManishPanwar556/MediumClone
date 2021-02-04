package com.example.mediumclone2.ui.articlesFeed

import androidx.lifecycle.*
import com.example.mediumclone2.repository.MediumRepository
import com.example.mediumclone2.retrofit.models.articles.Article
import com.example.mediumclone2.ui.home.ArticleActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedArticlesViewModel
@Inject constructor(
    private val repository: MediumRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles
    private var _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    init {
        feedArticles()
    }

    private fun feedArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.getUser()
            val token = user?.token
            val res = repository.feedArticles("Token $token")
            if (res.isSuccessful) {
                res.body()?.let {

                    if (it.articles.isEmpty()) {
                        _empty.postValue(true)
                    } else {
                        _articles.postValue(it.articles)
                    }
                }
            }
        }
    }
}