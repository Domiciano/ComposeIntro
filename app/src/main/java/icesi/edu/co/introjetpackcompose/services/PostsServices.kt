package icesi.edu.co.introjetpackcompose.services

import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import icesi.edu.co.introjetpackcompose.domain.model.Post
import kotlinx.coroutines.tasks.await

class PostsServices {
    suspend fun createPost(post: Post) {
        Firebase.firestore
            .collection("users")
            .document(post.author)
            .collection("posts")
            .document(post.id)
            .set(post)
            .await()
    }

    suspend fun getPostByUser(userID:String): QuerySnapshot {
        return Firebase.firestore
            .collection("users")
            .document(userID)
            .collection("posts")
            .get()
            .await()
    }

}