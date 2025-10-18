package com.example.foodsaver.data.network.dto

import com.google.gson.annotations.SerializedName

data class NotificationDto(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("userId")
    val userId: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("productId")
    val productId: Long? = null,

    @SerializedName("isRead")
    val isRead: Boolean = false,

    @SerializedName("createdAt")
    val createdAt: String
)