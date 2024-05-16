package icesi.edu.co.introjetpackcompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import icesi.edu.co.introjetpackcompose.domain.model.AppAuthState
import icesi.edu.co.introjetpackcompose.viewmodel.LoginViewModel
import icesi.edu.co.introjetpackcompose.viewmodel.SignupViewModel

@Composable
fun SignInScreen(navController: NavHostController, viewModel: LoginViewModel = viewModel() ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState by viewModel.authStatus.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Autenticación", style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        authState?.let {
            when (it) {
                is AppAuthState.Error -> {
                    Text(text = "Error: ${it.message}", color = Color.Red)
                }
                is AppAuthState.Loading -> {
                    CircularProgressIndicator()
                }
                is AppAuthState.Success -> {
                    navController.navigate("profile")
                }
            }
        }

        Button(onClick = {
            viewModel.login(email, password)
        }) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("No tengo cuenta, quiero resgistrame", color= Color.Blue, modifier = Modifier.clickable {
            navController.navigate("signup")
        })
    }
}
