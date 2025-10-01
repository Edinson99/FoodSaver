package com.example.foodsaver.ui
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun NotificationsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Notificaciones",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        NotificationCard(
            title = "Nueva donación de tomates",
            description = "Se ha publicado una nueva donación cerca de ti."
        )
        Spacer(Modifier.height(12.dp))
        NotificationCard(
            title = "Nuevo producto disponible",
            description = "¡Explora los productos recién agregados!"
        )
        Spacer(Modifier.height(12.dp))
        NotificationCard(
            title = "Tu producto se ha vendido",
            description = "Revisa los detalles de la transacción."
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { /* Acción de aceptar sesión */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
        ) {
            Text("Aceptar sesión")
        }
    }
}

@Composable
fun NotificationCard(title: String, description: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFE8F5E9),
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, color = Color(0xFF388E3C))
            Spacer(Modifier.height(4.dp))
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}