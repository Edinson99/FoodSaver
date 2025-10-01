package com.example.foodsaver
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.example.foodsaver.ui.*
import com.example.foodsaver.ui.theme.FoodsaverTheme
import com.example.foodsaver.ui.NotificationsScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodsaverTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "welcome") {
                    composable("welcome") {
                        WelcomeScreen(
                            onLogin = { navController.navigate("login") },
                            onRegister = { navController.navigate("register") }
                        )
                    }
                    composable("login") {
                        var loginError by remember { mutableStateOf(false) }
                        LoginScreen(
                            onLogin = { user, pass ->
                                if (user == "persona" && pass == "12345") {
                                    navController.navigate("home") {
                                        popUpTo("welcome") { inclusive = true }
                                    }
                                } else {
                                    loginError = true
                                }
                            },
                            showError = loginError
                        )
                    }
                    composable("register") {
                        RegisterScreen(
                            onRegister = { navController.popBackStack() }
                        )
                    }
                    composable("home") {
                        HomeScreen(
                            onPublishProduct = { navController.navigate("publish") },
                            onNotifications = { navController.navigate("notifications") },
                            onLogout = {
                                navController.navigate("welcome") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("publish") {
                        PublishProductScreen(
                            onPublish = { navController.popBackStack() }
                        )
                    }
                    composable("notifications") {
                        NotificationsScreen()
                    }
                }
            }
        }
    }
}