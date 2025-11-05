package com.foodsaver.repository

import com.foodsaver.model.Notification
import com.foodsaver.model.NotificationType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface NotificationRepository : JpaRepository<Notification, Long> {
    
    fun findByUserIdOrderByCreatedAtDesc(userId: Long): List<Notification>
    
    fun findByUserIdAndIsReadFalse(userId: Long): List<Notification>
    
    fun findByType(type: NotificationType): List<Notification>
    
    // NUEVO MÉTODO: Contar notificaciones no leídas
    fun countByUserIdAndIsReadFalse(userId: Long): Long
    
    // ALIAS para el método que usas en el service (opcional)
    fun countUnreadByUserId(userId: Long): Long = countByUserIdAndIsReadFalse(userId)
    
    // MÉTODO ADICIONAL: Verificar si existe alguna notificación no leída
    fun existsByUserIdAndIsReadFalse(userId: Long): Boolean
    
    // MÉTODO ADICIONAL: Buscar por usuario y tipo
    fun findByUserIdAndType(userId: Long, type: NotificationType): List<Notification>
    
    // MÉTODO ADICIONAL: Buscar por producto
    fun findByProductId(productId: Long): List<Notification>
    
    // QUERY PERSONALIZADA: Notificaciones recientes (últimos 7 días)
    @Query("SELECT n FROM Notification n WHERE n.userId = :userId AND n.createdAt >= :since ORDER BY n.createdAt DESC")
    fun findRecentNotifications(@Param("userId") userId: Long, @Param("since") since: java.time.LocalDateTime): List<Notification>
}