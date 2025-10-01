package com.foodsaver.model

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val timestamp: Long
)