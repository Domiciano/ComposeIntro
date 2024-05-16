package icesi.edu.co.introjetpackcompose.repository

import com.google.firebase.auth.FirebaseAuthException
import icesi.edu.co.introjetpackcompose.domain.model.AppAuthState
import icesi.edu.co.introjetpackcompose.domain.model.User
import icesi.edu.co.introjetpackcompose.services.AuthServices
import icesi.edu.co.introjetpackcompose.services.UserServices


interface AuthRepository {
    suspend fun login(email: String, pass: String): AppAuthState
    suspend fun signup(user: User, pass: String): AppAuthState
    fun signout()
}

class AuthRepositoryImpl(
    val authServices: AuthServices = AuthServices(),
    val userRepository: UserRepository = UserRepositoryImpl()
) : AuthRepository {
    override suspend fun login(email: String, pass: String): AppAuthState {
        try {
            val result = authServices.login(email, pass)
            result.user?.let {
                return AppAuthState.Success(it.uid)
            } ?: run {
                return AppAuthState.Error("Something went wrong")
            }
        } catch (ex: FirebaseAuthException) {
            return AppAuthState.Error(ex.errorCode)
        }
    }

    override suspend fun signup(user: User, pass: String): AppAuthState {
        try {
            //1. Registro
            val result = authServices.signup(user.email, pass)
            result.user?.let {
                //2. Guardar usuario en base de datos
                user.id = it.uid
                userRepository.createUser(user)
                return AppAuthState.Success(it.uid)
            } ?: run {
                return AppAuthState.Error("Something went wrong")
            }
        } catch (ex: FirebaseAuthException) {
            return AppAuthState.Error(ex.errorCode)
        }
    }

    override fun signout() {
        authServices.signout()
    }

}