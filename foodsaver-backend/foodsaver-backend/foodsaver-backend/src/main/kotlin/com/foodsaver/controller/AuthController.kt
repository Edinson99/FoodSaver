package com.foodsaver.controller

import com.foodsaver.model.User
import com.foodsaver.model.UserType
import com.foodsaver.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = ["*"])
class AuthController(
    private val userService: UserService
) {
    
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<AuthResponse> {
        return try {
            println("🔐 Intento de login para: ${loginRequest.email}")
            
            val user = userService.authenticateUser(loginRequest.email, loginRequest.password)
            if (user != null) {
                println("✅ Login exitoso para: ${loginRequest.email}")
                ResponseEntity.ok(AuthResponse(
                    success = true,
                    message = "Login exitoso",
                    user = user,
                    token = "simple_token_${user.id}"
                ))
            } else {
                println("❌ Login fallido para: ${loginRequest.email}")
                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(AuthResponse(
                        success = false,
                        message = "Email o contraseña incorrectos",
                        user = null,
                        token = null
                    ))
            }
        } catch (e: Exception) {
            println("💥 Error en login: ${e.message}")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(AuthResponse(
                    success = false,
                    message = "Error del servidor: ${e.message}",
                    user = null,
                    token = null
                ))
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<AuthResponse> {
        return try {
            println("📝 Intento de registro para: ${registerRequest.email}")
            
            // Validaciones básicas
            if (registerRequest.email.isBlank() || registerRequest.password.isBlank() || registerRequest.name.isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponse(
                        success = false,
                        message = "Todos los campos son obligatorios",
                        user = null,
                        token = null
                    ))
            }
            
            // Verificar si el email ya existe
            if (userService.existsByEmail(registerRequest.email)) {
                println("⚠️ Email ya existe: ${registerRequest.email}")
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(AuthResponse(
                        success = false,
                        message = "El email ya está registrado",
                        user = null,
                        token = null
                    ))
            }

            val newUser = User(
                email = registerRequest.email.trim().lowercase(),
                password = registerRequest.password,
                name = registerRequest.name.trim(),
                phone = registerRequest.phone.trim(),
                location = registerRequest.location.trim(),
                userType = UserType.valueOf(registerRequest.userType.uppercase())
            )

            val savedUser = userService.createUser(newUser)
            println("✅ Usuario registrado exitosamente: ${savedUser.email}")
            
            ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthResponse(
                    success = true,
                    message = "Usuario registrado exitosamente",
                    user = savedUser,
                    token = "simple_token_${savedUser.id}"
                ))
        } catch (e: IllegalArgumentException) {
            println("💥 Error de validación: ${e.message}")
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(AuthResponse(
                    success = false,
                    message = "Tipo de usuario inválido. Use VENDOR o BUYER",
                    user = null,
                    token = null
                ))
        } catch (e: Exception) {
            println("💥 Error en registro: ${e.message}")
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(AuthResponse(
                    success = false,
                    message = "Error al registrar: ${e.message}",
                    user = null,
                    token = null
                ))
        }
    }
    
    @PostMapping("/logout")
    fun logout(@RequestHeader("Authorization", required = false) token: String?): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(mapOf(
            "success" to true,
            "message" to "Logout exitoso"
        ))
    }
    
    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("🚀 Auth Controller funcionando correctamente!")
    }
    
    @GetMapping("/validate")
    fun validateToken(@RequestHeader("Authorization", required = false) token: String?): ResponseEntity<Map<String, Any>> {
        return if (token != null && token.startsWith("simple_token_")) {
            ResponseEntity.ok(mapOf(
                "valid" to true,
                "message" to "Token válido"
            ))
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mapOf(
                "valid" to false,
                "message" to "Token inválido"
            ))
        }
    }
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val location: String,
    val userType: String
)

data class AuthResponse(
    val success: Boolean,
    val message: String,
    val user: User?,
    val token: String?
)