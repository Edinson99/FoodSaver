// This file defines the Notification data model for the FoodSaverCompose application.

package com.foodsavercompose.model

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val timestamp: Long,
    val isRead: Boolean
)