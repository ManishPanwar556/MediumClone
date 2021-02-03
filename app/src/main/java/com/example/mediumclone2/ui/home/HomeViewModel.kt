package com.example.mediumclone2.ui.home

import androidx.lifecycle.*
import com.example.mediumclone2.repository.MediumRepository
import com.example.mediumclone2.retrofit.models.articles.Article
import com.example.mediumclone2.retrofit.models.postArticle.ArticlePost
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val repository: MediumRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel(){

    private var _articles=MutableLiveData<List<Article>>()
    private var _postArticleSuccess=MutableLiveData<Boolean>()
    val postArticleSuccess:LiveData<Boolean>
    get() = _postArticleSuccess
    val articles:LiveData<List<Article>>
    get() = _articles


    init {
        updateLiveData()
    }
    private fun updateLiveData(){
        viewModelScope.launch(Dispatchers.IO) {
            val res=repository.getAllArticles()
            if(res.isSuccessful){
                res.body()?.let {
                    _articles.postValue(it.articles)
                }
            }
        }
    }
    fun postArticle(articlePost: ArticlePost){
        viewModelScope.launch(Dispatchers.IO) {
            val user=repository.getUser()
            val res=repository.postArticle(articlePost,"Token ${user?.token}")
            if(res.isSuccessful){
                res.body()?.let{
                    _postArticleSuccess.postValue(true)
                }
            }
        }
    }
}