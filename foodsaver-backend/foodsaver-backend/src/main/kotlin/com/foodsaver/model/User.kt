package com.foodsaver.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(unique = true, nullable = false)
    val email: String,
    
    @Column(nullable = false)
    val password: String,
    
    @Column(nullable = false)
    val name: String,
    
    @Column(nullable = false)
    val phone: String,
    
    @Column(nullable = false)
    val location: String,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val userType: UserType,
    
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(nullable = false)
    val isActive: Boolean = true
)

enum class UserType {
    VENDOR, BUYER
}