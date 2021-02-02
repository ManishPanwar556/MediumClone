package com.example.mediumclone2.ui.home

import androidx.lifecycle.*
import com.example.mediumclone2.repository.MediumRepository
import com.example.mediumclone2.retrofit.models.articles.Article
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
}