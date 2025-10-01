package com.example.foodsaver.ui

import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onPublishProduct: () -> Unit,
    onNotifications: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bienvenido a FoodSaver", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onPublishProduct,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Publicar producto")
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onNotifications,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Notificaciones")
        }
        Spacer(Modifier.height(16.dp))
        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
    }
}