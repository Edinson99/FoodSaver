package com.foodsaver.service

import com.foodsaver.model.Notification
import com.foodsaver.model.NotificationType
import com.foodsaver.repository.NotificationRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository
) {
    
    fun getNotificationsByUser(userId: Long): List<Notification> {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId)
    }
    
    fun getUnreadNotifications(userId: Long): List<Notification> {
        return notificationRepository.findByUserIdAndIsReadOrderByCreatedAtDesc(userId, false)
    }
    
    fun createNotification(notification: Notification): Notification {
        return notificationRepository.save(notification.copy(
            createdAt = LocalDateTime.now()
        ))
    }
    
    fun markAsRead(id: Long): Notification? {
        val notification = notificationRepository.findById(id).orElse(null)
        return if (notification != null) {
            notificationRepository.save(notification.copy(isRead = true))
        } else {
            null
        }
    }
    
    fun getUnreadCount(userId: Long): Int {
        return notificationRepository.countUnreadByUserId(userId).toInt()
    }
    
    fun deleteNotification(id: Long): Boolean {
        return if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id)
            true
        } else {
            false
        }
    }
    
    fun markAllAsRead(userId: Long): List<Notification> {
        val unreadNotifications = getUnreadNotifications(userId)
        return unreadNotifications.map { notification ->
            notificationRepository.save(notification.copy(isRead = true))
        }
    }
    
    fun createProductNotification(
        userId: Long,
        productId: Long,
        title: String,
        message: String,
        type: NotificationType = NotificationType.NEW_PRODUCT
    ): Notification {
        val notification = Notification(
            userId = userId,
            title = title,
            message = message,
            type = type,
            productId = productId
        )
        return createNotification(notification)
    }
}