package com.example.mediumclone2.ui.login

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mediumclone2.repository.MediumRepository
import com.example.mediumclone2.retrofit.models.user.UserLogIn
import com.example.mediumclone2.room.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel
 @Inject constructor(
    private val repository: MediumRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _logInSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _logInSuccess
    private var _logInFailure = MutableLiveData<Boolean>()
    val loginFailure: LiveData<Boolean>
        get() = _logInFailure
    fun loginUser(userLogIn: UserLogIn) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.logInUser(userLogIn)
            if (res.isSuccessful) {
                res.body()?.let {
                    val user = it.user
                    val userEntity = UserEntity(
                        user.token,
                        user.username,
                        user.bio,
                        user.image?.toString(),
                        user.email
                    )
                    repository.insertUserToRoom(userEntity)
                    _logInSuccess.postValue(true)
                }
            }
            else{
                _logInFailure.postValue(true)
            }
        }

    }
}