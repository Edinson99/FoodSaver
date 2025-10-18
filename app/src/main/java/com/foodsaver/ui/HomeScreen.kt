package com.example.foodsaver.ui

import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(
    onPublishProduct: () -> Unit,
    onSearch: () -> Unit,
    onNotifications: () -> Unit,
    onReports: () -> Unit,
    onChatList: () -> Unit,
    onTestConnection: () -> Unit, // ← AGREGAR ESTE PARÁMETRO
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "FoodSaver",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF4CAF50)
        )
        Text(
            text = "Menos desperdicio, más oportunidades",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(32.dp))

        // Botón principal - Publicar producto
        Button(
            onClick = onPublishProduct,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
        ) {
            Text("Publicar producto")
        }

        Spacer(Modifier.height(16.dp))

        // Botón de búsqueda
        Button(
            onClick = onSearch,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar productos")
        }

        Spacer(Modifier.height(16.dp))

        // Botón de conversaciones
        Button(
            onClick = onChatList,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mis conversaciones")
        }

        Spacer(Modifier.height(16.dp))

        // Botón de reportes
        Button(
            onClick = onReports,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver reportes")
        }

        Spacer(Modifier.height(16.dp))

        // Botón de notificaciones
        Button(
            onClick = onNotifications,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Notificaciones")
        }

        Spacer(Modifier.height(32.dp))

        // AGREGAR ESTE BOTÓN ANTES DEL BOTÓN DE LOGOUT:
        Button(
            onClick = onTestConnection,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
        ) {
            Text("🔧 Probar Conexión Backend")
        }

        Spacer(Modifier.height(16.dp))

        // Botón de cerrar sesión
        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
    }
}