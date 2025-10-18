package com.example.foodsaver.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChatListScreen(
    onChatClick: (ChatRoom) -> Unit = {}
) {
    val chatRooms = getSampleChatRooms()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Mis conversaciones", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        if (chatRooms.isEmpty()) {
            // Estado vacío
            EmptyChatState()
        } else {
            LazyColumn {
                items(chatRooms) { chatRoom ->
                    ChatRoomItem(
                        chatRoom = chatRoom,
                        onClick = { onChatClick(chatRoom) }
                    )
                    if (chatRoom != chatRooms.last()) {
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ChatRoomItem(
    chatRoom: ChatRoom,
    onClick: () -> Unit
) {
    val timeFormat = SimpleDateFormat("dd/MM HH:mm", Locale.getDefault())

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar (inicial del producto)
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chatRoom.productName.take(1).uppercase(),
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = chatRoom.productName,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(4.dp))

                Text(
                    text = chatRoom.lastMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    maxLines = 1
                )
                Spacer(Modifier.height(4.dp))

                Text(
                    text = timeFormat.format(chatRoom.lastMessageTime),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                if (chatRoom.unreadCount > 0) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        Text(
                            text = chatRoom.unreadCount.toString(),
                            modifier = Modifier.padding(6.dp),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyChatState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "💬",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "No tienes conversaciones",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Cuando contactes a un vendedor o alguien te contacte, aparecerán aquí",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

// Modelo de datos para ChatRoom
data class ChatRoom(
    val id: String = "",
    val buyerId: String,
    val sellerId: String,
    val productId: String,
    val productName: String,
    val lastMessage: String = "",
    val lastMessageTime: Date = Date(),
    val unreadCount: Int = 0
)

// Datos de ejemplo
fun getSampleChatRooms(): List<ChatRoom> {
    return listOf(
        ChatRoom(
            id = "chat1",
            buyerId = "buyer1",
            sellerId = "seller1",
            productId = "prod1",
            productName = "Tomates frescos",
            lastMessage = "Perfecto, ¿dónde puedo recogerlos?",
            lastMessageTime = Date(System.currentTimeMillis() - 3000000),
            unreadCount = 2
        ),
        ChatRoom(
            id = "chat2",
            buyerId = "buyer2",
            sellerId = "seller1",
            productId = "prod2",
            productName = "Pan del día anterior",
            lastMessage = "Gracias por la donación",
            lastMessageTime = Date(System.currentTimeMillis() - 7200000),
            unreadCount = 0
        ),
        ChatRoom(
            id = "chat3",
            buyerId = "buyer3",
            sellerId = "seller1",
            productId = "prod3",
            productName = "Manzanas rojas",
            lastMessage = "¿Aún están disponibles?",
            lastMessageTime = Date(System.currentTimeMillis() - 10800000),
            unreadCount = 1
        )
    )
}