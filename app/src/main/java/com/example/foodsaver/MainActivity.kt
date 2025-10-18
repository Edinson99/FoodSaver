package com.example.foodsaver
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.navigation.NavController
import com.example.foodsaver.ui.*
import com.example.foodsaver.ui.theme.FoodsaverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodsaverTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "welcome"
                ) {
                    // Pantalla de bienvenida
                    composable("welcome") {
                        WelcomeScreen(
                            onLogin = { navController.navigate("login") },
                            onRegister = { navController.navigate("register") }
                        )
                    }

                    // Pantalla de login
                    composable("login") {
                        var loginError by remember { mutableStateOf(false) }
                        LoginScreen(
                            onLogin = { user, pass ->
                                if (user == "persona" && pass == "12345") {
                                    navController.navigate("home") {
                                        popUpTo("welcome") { inclusive = true }
                                    }
                                    loginError = false
                                } else {
                                    loginError = true
                                }
                            },
                            showError = loginError
                        )
                    }

                    // Pantalla de registro
                    composable("register") {
                        RegisterScreen(
                            onRegister = {
                                // Simular registro exitoso y navegar al home
                                navController.navigate("home") {
                                    popUpTo("welcome") { inclusive = true }
                                }
                            }
                        )
                    }

                    // Pantalla principal (home)
                    composable("home") {
                        HomeScreen(
                            onPublishProduct = { navController.navigate("publish") },
                            onSearch = { navController.navigate("search") },
                            onNotifications = { navController.navigate("notifications") },
                            onReports = { navController.navigate("reports") },
                            onChatList = { navController.navigate("chat_list") },
                            onTestConnection = { navController.navigate("test_connection") }, // ← AGREGAR
                            onLogout = {
                                navController.navigate("welcome") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        )
                    }

                    // Pantalla de publicar producto
                    composable("publish") {
                        PublishProductScreen(
                            onPublish = {
                                // Después de publicar, volver al home
                                navController.popBackStack()
                            }
                        )
                    }

                    // Pantalla de búsqueda
                    composable("search") {
                        SearchScreen()
                    }

                    // Pantalla de notificaciones
                    composable("notifications") {
                        NotificationsScreen()
                    }

                    // Pantalla de reportes
                    composable("reports") {
                        ReportsScreen()
                    }

                    // Pantalla de lista de chats
                    composable("chat_list") {
                        ChatListScreen(
                            onChatClick = { chatRoom ->
                                navController.navigate("chat/${chatRoom.id}/${chatRoom.productName}/${chatRoom.sellerId}")
                            }
                        )
                    }

                    // Pantalla de chat individual
                    composable("chat/{chatId}/{productName}/{otherUserId}") { backStackEntry ->
                        val chatId = backStackEntry.arguments?.getString("chatId") ?: ""
                        val productName = backStackEntry.arguments?.getString("productName") ?: ""
                        val otherUserId = backStackEntry.arguments?.getString("otherUserId") ?: ""

                        // Aquí deberías obtener el nombre del usuario basado en otherUserId
                        val otherUserName = "Usuario" // Por ahora, un nombre genérico

                        ChatScreen(
                            productName = productName,
                            otherUserName = otherUserName,
                            onSendMessage = { message ->
                                // Implementar envío de mensaje
                                // Por ahora, solo simular
                                println("Enviando mensaje: $message")
                            }
                        )
                    }

                    // Pantalla de detalle de producto
                    composable("product_detail/{productId}") { backStackEntry ->
                        val productId = backStackEntry.arguments?.getString("productId") ?: ""

                        ProductDetailScreen(
                            productId = productId, // Solo este parámetro
                            onBackPressed = {
                                navController.popBackStack()
                            },
                            onContactSeller = {
                                navController.navigate("chat/new/$productId")
                            }
                        )
                    }

                    // Pantalla de prueba de conexión
                    composable("test_connection") {
                        TestConnectionScreen()
                    }
                }
            }
        }
    }
}