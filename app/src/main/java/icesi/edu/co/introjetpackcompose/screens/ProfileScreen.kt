package icesi.edu.co.introjetpackcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import icesi.edu.co.introjetpackcompose.R

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // Imagen de perfil circular
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre del perfil
        Text(
            text = "Nombre del Perfil",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Correo electrónico
        Text(
            text = "correo@ejemplo.com",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de comentarios
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }

}