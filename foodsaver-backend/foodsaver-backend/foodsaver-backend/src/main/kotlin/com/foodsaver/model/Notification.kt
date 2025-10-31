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
    val title: String = "",

    @Column(length = 1000, nullable = false)
    val message: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: NotificationType = NotificationType.GENERAL,

    @Column(nullable = false)
    val userId: Long = 0,

    @Column
    val productId: Long? = null,

    @Column(nullable = false)
    val isRead: Boolean = false,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)