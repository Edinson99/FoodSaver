package com.foodsaver.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "notifications")
data class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    val userId: Long,
    
    @Column(nullable = false)
    val title: String,
    
    @Column(nullable = false)
    val message: String,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: NotificationType,
    
    val productId: Long? = null,
    
    @Column(nullable = false)
    val timestamp: LocalDateTime = LocalDateTime.now(),
    
    @Column(nullable = false)
    val isRead: Boolean = false
)

enum class NotificationType {
    PRODUCT_EXPIRING,
    PRODUCT_INTEREST,
    PURCHASE_CONFIRMATION,
    SYSTEM_NOTIFICATION
}