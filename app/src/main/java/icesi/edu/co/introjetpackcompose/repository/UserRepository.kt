package icesi.edu.co.introjetpackcompose.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import icesi.edu.co.introjetpackcompose.domain.model.Post
import icesi.edu.co.introjetpackcompose.domain.model.User
import icesi.edu.co.introjetpackcompose.services.UserServices
import java.util.UUID

interface UserRepository {
    suspend fun loadUser(): User?
    suspend fun createUser(user: User)
}

class UserRepositoryImpl(
    val userServices: UserServices = UserServices(),
    val postRepository: PostRepository = PostRepositoryImpl()
) : UserRepository {
    override suspend fun loadUser(): User? {
        val document = userServices.loadUser(Firebase.auth.uid!!)
        //Document -> Obj
        //DTO -> Domain model
        val user = document.toObject(User::class.java)
        return user
    }

    override suspend fun createUser(user: User) {
        userServices.createUser(user)

        //Se crean 10 post dummy
        postRepository.sendPost(
            Post(UUID.randomUUID().toString(),
                Firebase.auth.uid!!, "Just had the best coffee ever!")
        )
        postRepository.sendPost(
            Post(UUID.randomUUID().toString(),
                Firebase.auth.uid!!, "Anyone up for a movie tonight?")
        )
        postRepository.sendPost(
            Post(UUID.randomUUID().toString(),
                Firebase.auth.uid!!, "Loving the weather today!")
        )
        postRepository.sendPost(
            Post(UUID.randomUUID().toString(),
                Firebase.auth.uid!!, "Can't believe it's already Friday.")
        )
        postRepository.sendPost(
            Post(UUID.randomUUID().toString(),
                Firebase.auth.uid!!, "Just finished a great workout.")
        )
        postRepository.sendPost(
            Post(UUID.randomUUID().toString(),
                Firebase.auth.uid!!, "Reading a fascinating book right now.")
        )
        postRepository.sendPost(
            Post(UUID.randomUUID().toString(),
                Firebase.auth.uid!!, "Who else is excited for the weekend?")
        )
        postRepository.sendPost(
            Post(UUID.randomUUID().toString(),
                Firebase.auth.uid!!, "Had an amazing lunch with friends.")
        )
        postRepository.sendPost(
            Post(UUID.randomUUID().toString(),
                Firebase.auth.uid!!, "Learning something new every day.")
        )
        postRepository.sendPost(
            Post(UUID.randomUUID().toString(),
                Firebase.auth.uid!!, "Spent the afternoon at the park. So relaxing!")
        )

    }

}