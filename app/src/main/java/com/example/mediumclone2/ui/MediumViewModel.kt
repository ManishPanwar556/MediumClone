package com.example.mediumclone2.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mediumclone2.repository.MediumRepository
import com.example.mediumclone2.retrofit.models.articles.Article
import com.example.mediumclone2.retrofit.models.user.UserLogIn
import com.example.mediumclone2.retrofit.models.user.UserRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MediumViewModel
 @Inject constructor(
    private val repository: MediumRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _userNotNull = MutableLiveData<Boolean>()
    val notNull: LiveData<Boolean>
        get() = _userNotNull
    private var _userNull = MutableLiveData<Boolean>()
    val userNull: LiveData<Boolean>
        get() = _userNull
    private var _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    init {
        updateArticlesLiveData()
    }

    private fun updateArticlesLiveData() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.getAllArticles()
            if (res.isSuccessful) {
                res.body()?.let {
                    _articles.postValue(it.articles)
                }
            }
        }
    }

  fun getUser(){
      viewModelScope.launch(Dispatchers.IO) {
          val user=repository.getUser()
          if(user!=null){
              _userNotNull.postValue(true)
          }
          else{
              _userNull.postValue(true)
          }
      }
  }

}