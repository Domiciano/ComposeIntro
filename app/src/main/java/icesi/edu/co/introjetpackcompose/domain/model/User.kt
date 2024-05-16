package icesi.edu.co.introjetpackcompose.domain.model

data class User(
    var id:String = "",
    var username:String = "",
    var email:String = "",
    var name:String = "",
    var profilePic:String? = null
)