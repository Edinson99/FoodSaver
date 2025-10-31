package com.foodsaver.repository

import com.foodsaver.model.Notification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationRepository : JpaRepository<Notification, Long> {
    fun findByUserIdOrderByTimestampDesc(userId: Long): List<Notification>
    fun findByUserIdAndIsReadFalseOrderByTimestampDesc(userId: Long): List<Notification>
    fun countByUserIdAndIsReadFalse(userId: Long): Int
}