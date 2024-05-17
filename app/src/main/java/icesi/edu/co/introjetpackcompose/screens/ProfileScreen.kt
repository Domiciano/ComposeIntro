package icesi.edu.co.introjetpackcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import icesi.edu.co.introjetpackcompose.domain.model.Post
import icesi.edu.co.introjetpackcompose.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = viewModel()) {

    val isAuthenticated by remember { mutableStateOf(Firebase.auth.currentUser != null) }
    val userState by viewModel.userState.observeAsState()
    val userPost by viewModel.postState.observeAsState()

    if (isAuthenticated) {
        LaunchedEffect(true) {
            viewModel.loadUser()
            viewModel.loadPosts()
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "Salir", modifier = Modifier
                .align(Alignment.Start)
                .clickable {
                    viewModel.signout()
                    navController.navigate("signin")
                })
            // Imagen de perfil circular
            Image(
                painter = rememberAsyncImagePainter("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiQyCrAWdIb8-moiYuP7EdpznRLOLaKoZWJ04MLzMi1wkJrMfLKQshwXhB_ODNz3T6_aoOwQ0YccVpSbLO2K9qkpx-HTklvNm3ZR_spOINLr861_PgDXDnh6LgpptIyzR5Nv-UjlQ-5FyeLpHwOCb4NjZ8darLIomTVjHM2VvDv7YZdzO-FS6zMKEhlCQ/s1600/Android-JetpackCompose1.2-Social.png"),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre del perfil
            Text(
                text = "${userState?.name}",
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Correo electrónico
            Text(
                text = "${userState?.email}",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Lista de comentarios
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                userPost?.let {
                    items(it) {post ->
                        PostCard(post)
                    }
                }
            }
        }
    } else {
        navController.navigate("signin")
    }

}

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = post.date.toDate().toString(), style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = post.message, style = MaterialTheme.typography.bodyMedium)
        }
    }
}