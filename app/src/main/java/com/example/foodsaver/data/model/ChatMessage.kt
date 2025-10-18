package com.example.foodsaver.data.model

import java.util.Date

data class ChatMessage(
    val id: String = "",
    val senderId: String,
    val receiverId: String,
    val productId: String,
    val message: String,
    val timestamp: Date = Date(),
    val isRead: Boolean = false
)

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