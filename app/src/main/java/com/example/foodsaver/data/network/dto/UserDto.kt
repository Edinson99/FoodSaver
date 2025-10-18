package com.example.foodsaver.data.network.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class UserDto(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String? = null, // Solo para registro/login

    @SerializedName("name")
    val name: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("userType")
    val userType: String, // "VENDOR" o "BUYER"

    @SerializedName("createdAt")
    val createdAt: String? = null,

    @SerializedName("isActive")
    val isActive: Boolean = true
)

data class LoginRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)

data class RegisterRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("userType")
    val userType: String
)

data class AuthResponse(
    @SerializedName("token")
    val token: String? = null,

    @SerializedName("user")
    val user: UserDto,

    @SerializedName("message")
    val message: String? = null
)