package com.example.mediumclone2.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mediumclone2.repository.MediumRepository
import com.example.mediumclone2.retrofit.models.articles.Article
import com.example.mediumclone2.retrofit.models.user.UserLogIn
import com.example.mediumclone2.retrofit.models.user.UserRegister
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MediumViewModel
@ViewModelInject constructor(
    private val repository: MediumRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _isSignInSuccess = MutableLiveData<Boolean>()
    val isSignInSuccess: LiveData<Boolean>
        get() = _isSignInSuccess
    private var _isSignUpSuccess = MutableLiveData<Boolean>()
    val isSignUpSuccess: LiveData<Boolean>
        get() = _isSignUpSuccess
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

    fun signIn(userLogIn: UserLogIn) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.logInUser(userLogIn)
            if (res.isSuccessful) {
                res.body()?.let {
                    _isSignInSuccess.postValue(true)
                }
            }
        }
    }
    fun signUp(userRegister: UserRegister){
        viewModelScope.launch(Dispatchers.IO) {
            val res=repository.signUpUser(userRegister)
            if(res.isSuccessful){
                res.body()?.let {
                    _isSignUpSuccess.postValue(true)
                }
            }
        }
    }

}