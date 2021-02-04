package com.example.mediumclone2.ui.signUp

import androidx.lifecycle.*
import com.example.mediumclone2.repository.MediumRepository
import com.example.mediumclone2.retrofit.models.user.UserRegister
import com.example.mediumclone2.room.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject constructor(
    private val repository: MediumRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean>
        get() = _signUpSuccess
    private var _signUpFailure = MutableLiveData<Boolean>()
    val signUpFailure: LiveData<Boolean>
        get() = _signUpFailure

    fun signUp(userRegister: UserRegister) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.signUpUser(userRegister)
            if (res.isSuccessful) {
                res.body()?.let {
                    val user = it.user
                    repository.deleteUser()
                    val userEntity = UserEntity(
                        user.token,
                        user.username,
                        user.bio,
                        user.image?.toString(),
                        user.email
                    )
                    repository.insertUserToRoom(userEntity)
                    _signUpSuccess.postValue(true)
                }

            } else {
                _signUpFailure.postValue(true)
            }
        }

    }
}