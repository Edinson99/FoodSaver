package com.example.foodsaver.data.network.api

import com.example.foodsaver.data.network.dto.*
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @GET("api/users/{id}")
    suspend fun getUserById(@Path("id") userId: Long): Response<UserDto>

    @GET("api/users/profile")
    suspend fun getCurrentUser(@Header("Authorization") token: String): Response<UserDto>

    @PUT("api/users/{id}")
    suspend fun updateUser(
        @Path("id") userId: Long,
        @Body user: UserDto,
        @Header("Authorization") token: String
    ): Response<UserDto>

    @GET("api/users")
    suspend fun getAllUsers(@Header("Authorization") token: String): Response<List<UserDto>>
}