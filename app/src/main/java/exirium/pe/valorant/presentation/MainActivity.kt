package exirium.pe.valorant.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import exirium.pe.valorant.presentation.screen.agents.AgentsScreen
import exirium.pe.valorant.presentation.screen.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = remember { true }
            val navController = rememberNavController()

            systemUiController.setStatusBarColor(
                color = Color(0xFF1C252E),
                darkIcons = useDarkIcons
            )

            NavHost(navController, startDestination = "splash") {
                composable("splash") { SplashScreen(navController) }
                composable("agentList") { AgentsScreen() }
            }
        }
    }
}