package com.example.foodsaver.data.model

import java.util.Date

data class User(
    val id: String = "",
    val email: String,
    val name: String,
    val phone: String,
    val location: String,
    val userType: UserType,
    val createdAt: Date = Date(),
    val isActive: Boolean = true
)

enum class UserType {
    VENDOR, // Vendedor
    BUYER   // Comprador
}