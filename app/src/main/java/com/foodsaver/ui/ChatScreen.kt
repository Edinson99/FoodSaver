package com.example.foodsaver.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChatScreen(
    productName: String,
    otherUserName: String,
    messages: List<ChatMessage> = getSampleMessages(),
    currentUserId: String = "user1",
    onSendMessage: (String) -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    var messageText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header con botón de regreso
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackPressed
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = otherUserName,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        text = "Producto: $productName",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Lista de mensajes
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                MessageBubble(
                    message = message,
                    isFromCurrentUser = message.senderId == currentUserId
                )
                Spacer(Modifier.height(4.dp))
            }
        }

        // Campo de entrada de mensaje
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    placeholder = { Text("Escribe un mensaje...") },
                    modifier = Modifier.weight(1f),
                    maxLines = 3
                )
                Spacer(Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            onSendMessage(messageText)
                            messageText = ""
                        }
                    }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Enviar")
                }
            }
        }
    }
}

@Composable
fun MessageBubble(
    message: ChatMessage,
    isFromCurrentUser: Boolean
) {
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isFromCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        if (!isFromCurrentUser) {
            Spacer(Modifier.width(48.dp))
        }

        Surface(
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (isFromCurrentUser) 16.dp else 4.dp,
                bottomEnd = if (isFromCurrentUser) 4.dp else 16.dp
            ),
            color = if (isFromCurrentUser)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isFromCurrentUser) Color.White else Color.Black
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = timeFormat.format(message.timestamp),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isFromCurrentUser)
                        Color.White.copy(alpha = 0.7f)
                    else
                        Color.Black.copy(alpha = 0.6f)
                )
            }
        }

        if (isFromCurrentUser) {
            Spacer(Modifier.width(48.dp))
        }
    }
}

// Modelo de datos para ChatMessage
data class ChatMessage(
    val id: String = "",
    val senderId: String,
    val receiverId: String,
    val productId: String,
    val message: String,
    val timestamp: Date = Date(),
    val isRead: Boolean = false
)

// Datos de ejemplo
fun getSampleMessages(): List<ChatMessage> {
    return listOf(
        ChatMessage(
            id = "1",
            senderId = "user2",
            receiverId = "user1",
            productId = "prod1",
            message = "Hola, ¿están disponibles los tomates?",
            timestamp = Date(System.currentTimeMillis() - 3600000)
        ),
        ChatMessage(
            id = "2",
            senderId = "user1",
            receiverId = "user2",
            productId = "prod1",
            message = "¡Hola! Sí, están disponibles. Son 2kg de tomates frescos.",
            timestamp = Date(System.currentTimeMillis() - 3500000)
        ),
        ChatMessage(
            id = "3",
            senderId = "user2",
            receiverId = "user1",
            productId = "prod1",
            message = "Perfecto, ¿dónde puedo recogerlos?",
            timestamp = Date(System.currentTimeMillis() - 3000000)
        ),
        ChatMessage(
            id = "4",
            senderId = "user1",
            receiverId = "user2",
            productId = "prod1",
            message = "Puedes pasar por mi tienda en la carrera 15 con calle 20. Estoy disponible hasta las 6 PM.",
            timestamp = Date(System.currentTimeMillis() - 2800000)
        )
    )
}