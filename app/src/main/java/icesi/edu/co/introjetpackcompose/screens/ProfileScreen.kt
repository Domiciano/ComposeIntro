package icesi.edu.co.introjetpackcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import icesi.edu.co.introjetpackcompose.domain.model.Post

@Composable
fun ProfileScreen() {


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Salir", modifier = Modifier
            .align(Alignment.Start)
            )
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
            text = "Fulanito de tales",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Correo electrónico
        Text(
            text = "fulanito@gmail.com",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

