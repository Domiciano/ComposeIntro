package icesi.edu.co.introjetpackcompose.repository

import icesi.edu.co.introjetpackcompose.domain.model.Post
import icesi.edu.co.introjetpackcompose.services.PostsServices

interface PostRepository {
    suspend fun sendPost(post: Post)
    suspend fun getPostOfUserID(userID: String): ArrayList<Post>
}

class PostRepositoryImpl(
    val postsServices: PostsServices = PostsServices(),
) : PostRepository {
    override suspend fun sendPost(post: Post) {
        postsServices.createPost(post)
    }

    override suspend fun getPostOfUserID(userID: String): ArrayList<Post> {
        return ArrayList(postsServices.getPostByUser(userID).documents.mapNotNull {
            it.toObject(Post::class.java)
        })
    }


}