package com.foodsaver.controller

import com.foodsaver.model.User
import com.foodsaver.model.UserType
import com.foodsaver.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = ["*"])
class UserController(
    private val userService: UserService
) {
    
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok(users)
    }
    
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val user = userService.findById(id)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/email/{email}")
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<User> {
        val user = userService.findByEmail(email)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/vendors")
    fun getVendors(): ResponseEntity<List<User>> {
        val vendors = userService.getUsersByType(UserType.VENDOR)
        return ResponseEntity.ok(vendors)
    }
    
    @GetMapping("/buyers")
    fun getBuyers(): ResponseEntity<List<User>> {
        val buyers = userService.getUsersByType(UserType.BUYER)
        return ResponseEntity.ok(buyers)
    }
    
    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updateRequest: UpdateUserRequest): ResponseEntity<User> {
        return try {
            val updatedUser = User(
                email = updateRequest.email,
                password = updateRequest.password,
                name = updateRequest.name,
                phone = updateRequest.phone,
                location = updateRequest.location,
                userType = UserType.valueOf(updateRequest.userType.uppercase())
            )
            
            val result = userService.updateUser(id, updatedUser)
            if (result != null) {
                ResponseEntity.ok(result)
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }
    
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return if (userService.deleteUser(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("🚀 User Controller funcionando correctamente!")
    }
}

data class UpdateUserRequest(
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val location: String,
    val userType: String
)