package icesi.edu.co.introjetpackcompose.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import icesi.edu.co.introjetpackcompose.domain.model.User
import icesi.edu.co.introjetpackcompose.services.UserServices
import java.io.File

interface UserRepository {
    suspend fun loadUser() : User?
    suspend fun createUser(user:User)
}

class UserRepositoryImpl(
    val userServices: UserServices = UserServices(),
) : UserRepository{
    override suspend fun loadUser(): User? {
        val document = userServices.loadUser(Firebase.auth.uid!!)
        //Document -> Obj
        //DTO -> Domain model
        val user = document.toObject(User::class.java)
        return user
    }

    override suspend fun createUser(user: User) {
        userServices.createUser(user)
    }

}