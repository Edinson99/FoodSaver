package com.foodsaver.controller

import com.foodsaver.model.Notification
import com.foodsaver.service.NotificationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = ["*"])
class NotificationController(
    private val notificationService: NotificationService
) {

    // Obtener todas las notificaciones de un usuario
    @GetMapping("/user/{userId}")
    fun getUserNotifications(@PathVariable userId: Long): ResponseEntity<List<Notification>> {
        return try {
            val notifications = notificationService.getNotificationsByUser(userId)
            ResponseEntity.ok(notifications)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    // Obtener solo notificaciones no leídas
    @GetMapping("/user/{userId}/unread")
    fun getUnreadNotifications(@PathVariable userId: Long): ResponseEntity<List<Notification>> {
        return try {
            val notifications = notificationService.getUnreadNotifications(userId)
            ResponseEntity.ok(notifications)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    // Crear una nueva notificación
    @PostMapping
    fun createNotification(@RequestBody notificationRequest: CreateNotificationRequest): ResponseEntity<Notification> {
        return try {
            val notification = Notification(
                userId = notificationRequest.userId,
                title = notificationRequest.title,
                message = notificationRequest.message,
                type = notificationRequest.type,
                productId = notificationRequest.productId
            )
            val savedNotification = notificationService.createNotification(notification)
            ResponseEntity.status(HttpStatus.CREATED).body(savedNotification)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }

    // Marcar una notificación como leída
    @PutMapping("/{id}/read")
    fun markAsRead(@PathVariable id: Long): ResponseEntity<Notification> {
        return try {
            val notification = notificationService.markAsRead(id)
            if (notification != null) {
                ResponseEntity.ok(notification)
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    // Obtener conteo de notificaciones no leídas
    @GetMapping("/user/{userId}/unread-count")
    fun getUnreadCount(@PathVariable userId: Long): ResponseEntity<Map<String, Int>> {
        return try {
            val count = notificationService.getUnreadCount(userId)
            ResponseEntity.ok(mapOf("unreadCount" to count))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    // Eliminar una notificación
    @DeleteMapping("/{id}")
    fun deleteNotification(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            val deleted = notificationService.deleteNotification(id)
            if (deleted) {
                ResponseEntity.noContent().build()
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}

// DTOs simplificados
data class CreateNotificationRequest(
    val userId: Long,
    val title: String,
    val message: String,
    val type: com.foodsaver.model.NotificationType, // Usar nombre completo
    val productId: Long? = null
)