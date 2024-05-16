package icesi.edu.co.introjetpackcompose.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import icesi.edu.co.introjetpackcompose.domain.model.Message
import icesi.edu.co.introjetpackcompose.domain.model.User
import icesi.edu.co.introjetpackcompose.repository.AuthRepository
import icesi.edu.co.introjetpackcompose.repository.AuthRepositoryImpl
import icesi.edu.co.introjetpackcompose.repository.UserRepository
import icesi.edu.co.introjetpackcompose.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class ProfileViewModel(
    val userRepo: UserRepository = UserRepositoryImpl(),
    val authRepo: AuthRepository = AuthRepositoryImpl(),
) : ViewModel() {

    //Estado
    private val _userState = MutableLiveData<User>()
    val userState:LiveData<User> get() = _userState

    private val messages = arrayListOf<Message>()
    private val _messagesState = MutableLiveData<ArrayList<Message>>(messages)
    val messagesState:LiveData<ArrayList<Message>> get() = _messagesState


    //Los eventos de entrada
    fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepo.loadUser()
            user?.let {
                withContext(Dispatchers.Main) {
                    _userState.value = it
                }
            }
        }
    }
    
    fun signout() {
        authRepo.signout()
    }

}