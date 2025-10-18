package com.example.foodsaver.data.model

import java.util.Date

data class Notification(
    val id: String = "",
    val userId: String,
    val title: String,
    val message: String,
    val type: NotificationType,
    val productId: String? = null,
    val isRead: Boolean = false,
    val createdAt: Date = Date()
)

enum class NotificationType {
    NEW_PRODUCT,    // Nuevo producto disponible
    PRICE_DROP,     // Bajada de precio
    EXPIRY_SOON,    // Producto próximo a vencer
    CHAT_MESSAGE,   // Nuevo mensaje
    PRODUCT_SOLD,   // Producto vendido
    PRODUCT_DONATED // Producto donado
}