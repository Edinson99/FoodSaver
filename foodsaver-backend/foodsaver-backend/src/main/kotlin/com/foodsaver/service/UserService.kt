package com.foodsaver.service

import com.foodsaver.model.User
import com.foodsaver.model.UserType
import com.foodsaver.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(user: User): User {
        println("💾 Guardando usuario: ${user.email}")
        return userRepository.save(user)
    }

    fun authenticateUser(email: String, password: String): User? {
        println("🔍 Buscando usuario por email: $email")
        val user = userRepository.findByEmail(email.trim().lowercase())
        
        return if (user != null && user.password == password) {
            println("✅ Autenticación exitosa para: $email")
            user
        } else {
            println("❌ Autenticación fallida para: $email")
            null
        }
    }

    fun existsByEmail(email: String): Boolean {
        val exists = userRepository.existsByEmail(email.trim().lowercase())
        println("📧 ¿Email $email existe? $exists")
        return exists
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email.trim().lowercase())
    }

    fun findById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUsersByType(userType: UserType): List<User> {
        return userRepository.findByUserType(userType)
    }

    fun updateUser(user: User): User {
        return userRepository.save(user)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    fun getUserCount(): Long {
        return userRepository.count()
    }
}