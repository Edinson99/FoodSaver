package com.foodsaver.service

import com.foodsaver.model.User
import com.foodsaver.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    
    fun createUser(user: User): User {
        if (userRepository.existsByEmail(user.email)) {
            throw IllegalArgumentException("El email ya está registrado")
        }
        
        val encodedUser = user.copy(
            password = passwordEncoder.encode(user.password)
        )
        
        return userRepository.save(encodedUser)
    }
    
    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email).orElse(null)
    }
    
    fun authenticateUser(email: String, password: String): User? {
        val user = findByEmail(email) ?: return null
        
        return if (passwordEncoder.matches(password, user.password)) {
            user
        } else {
            null
        }
    }
    
    fun getAllUsers(): List<User> = userRepository.findAll()
    
    fun findById(id: Long): User? = userRepository.findById(id).orElse(null)
    
    // Methods for UserController
    fun registerUser(user: User): User {
        return createUser(user)
    }
    
    fun loginUser(user: User): String {
        val authenticatedUser = authenticateUser(user.email, user.password)
        return if (authenticatedUser != null) {
            // In a real application, you would generate a JWT token here
            "login-success-token-${authenticatedUser.id}"
        } else {
            throw IllegalArgumentException("Invalid credentials")
        }
    }
    
    fun getUserProfile(id: Long): User {
        return findById(id) ?: throw IllegalArgumentException("User not found")
    }
    
    fun updateUserProfile(id: Long, user: User): User {
        val existingUser = findById(id) ?: throw IllegalArgumentException("User not found")
        val updatedUser = user.copy(id = id, createdAt = existingUser.createdAt)
        return userRepository.save(updatedUser)
    }
}