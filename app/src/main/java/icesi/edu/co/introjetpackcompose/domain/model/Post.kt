package icesi.edu.co.introjetpackcompose.domain.model

import com.google.firebase.Timestamp

data class Post(
    var id: String = "",
    var author: String = "",
    var message: String = "",
    var date: Timestamp = Timestamp.now()
)