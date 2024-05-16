package icesi.edu.co.introjetpackcompose.services

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import icesi.edu.co.introjetpackcompose.domain.model.User
import kotlinx.coroutines.tasks.await

class UserServices {

    //CRUD

    suspend fun createUser(user: User) {
        Firebase.firestore.collection("users").document(user.id).set(user).await()
    }

    suspend fun loadUser(id: String): DocumentSnapshot {
        val output = Firebase.firestore.collection("users").document(id).get().await()
        return output
    }

    suspend fun loadUserList(): QuerySnapshot {
        val output = Firebase.firestore.collection("users").get().await()
        return output
    }



}