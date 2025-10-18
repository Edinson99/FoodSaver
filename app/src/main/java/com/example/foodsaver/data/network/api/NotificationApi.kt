package com.example.foodsaver.data.network.api

import com.example.foodsaver.data.network.dto.NotificationDto
import retrofit2.Response
import retrofit2.http.*

interface NotificationApi {

    @GET("api/notifications/user/{userId}")
    suspend fun getUserNotifications(
        @Path("userId") userId: Long,
        @Header("Authorization") token: String
    ): Response<List<NotificationDto>>

    @GET("api/notifications/user/{userId}/unread")
    suspend fun getUnreadNotifications(
        @Path("userId") userId: Long,
        @Header("Authorization") token: String
    ): Response<List<NotificationDto>>

    @GET("api/notifications/user/{userId}/unread-count")
    suspend fun getUnreadCount(
        @Path("userId") userId: Long,
        @Header("Authorization") token: String
    ): Response<Map<String, Int>>

    @PUT("api/notifications/{id}/read")
    suspend fun markAsRead(
        @Path("id") notificationId: Long,
        @Header("Authorization") token: String
    ): Response<NotificationDto>

    @DELETE("api/notifications/{id}")
    suspend fun deleteNotification(
        @Path("id") notificationId: Long,
        @Header("Authorization") token: String
    ): Response<Void>
}