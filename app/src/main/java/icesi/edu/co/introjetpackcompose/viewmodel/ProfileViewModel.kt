package icesi.edu.co.introjetpackcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import icesi.edu.co.introjetpackcompose.domain.model.Post
import icesi.edu.co.introjetpackcompose.domain.model.User
import icesi.edu.co.introjetpackcompose.repository.AuthRepository
import icesi.edu.co.introjetpackcompose.repository.AuthRepositoryImpl
import icesi.edu.co.introjetpackcompose.repository.PostRepository
import icesi.edu.co.introjetpackcompose.repository.PostRepositoryImpl
import icesi.edu.co.introjetpackcompose.repository.UserRepository
import icesi.edu.co.introjetpackcompose.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    val userRepo: UserRepository = UserRepositoryImpl(),
    val messagesRepo: PostRepository = PostRepositoryImpl(),
    val authRepo: AuthRepository = AuthRepositoryImpl(),
) : ViewModel() {

    //Estado
    private val _userState = MutableLiveData<User>()
    val userState:LiveData<User> get() = _userState




    private var posts = listOf<Post>()
    private val _postsState = MutableLiveData<List<Post>>(posts)
    val postState:LiveData<List<Post>> get() = _postsState


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

    fun loadPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.auth.uid?.let {
                posts = messagesRepo.getPostOfUserID(it)
                withContext(Dispatchers.Main) {
                    _postsState.value = posts
                }
            }
        }
    }

}