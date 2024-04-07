package com.thucthantinh.createpassword.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thucthantinh.createpassword.domain.navigation.Destination
import com.thucthantinh.createpassword.domain.navigation.NavHostNoteApp
import com.thucthantinh.createpassword.domain.navigation.composable
import com.thucthantinh.createpassword.presentation.ui.home.HomeScreen
import com.thucthantinh.createpassword.presentation.ui.theme.CreatePasswordTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            CreatePasswordTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavigation(navController = navController)
                }
            }
        }
    }


}

@Composable
private fun SetupNavigation(navController: NavHostController) {
    NavHostNoteApp(navController = navController, startDestination = Destination.HomeScreen) {
        composable(Destination.HomeScreen) { HomeScreen(navController) }
    }
}