package com.foodsaver.repository

import com.foodsaver.model.User
import com.foodsaver.model.UserType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun findByUserType(userType: UserType): List<User>
    fun existsByEmail(email: String): Boolean  // ESTE MÉTODO ES IMPORTANTE
}