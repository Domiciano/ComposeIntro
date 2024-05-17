package icesi.edu.co.introjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import icesi.edu.co.introjetpackcompose.screens.ProfileScreen
import icesi.edu.co.introjetpackcompose.screens.SignInScreen
import icesi.edu.co.introjetpackcompose.screens.SignUpScreen
import icesi.edu.co.introjetpackcompose.ui.theme.IntroJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App(){
    Text("Hello world")
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntroJetpackComposeTheme {
        App()
    }
}