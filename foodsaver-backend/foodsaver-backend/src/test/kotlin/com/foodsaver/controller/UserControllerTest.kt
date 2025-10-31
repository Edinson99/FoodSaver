package com.foodsaver.controller

import com.foodsaver.service.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(UserController::class)
class UserControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {
    
    @MockBean
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        reset(userService)
    }

    @Test
    fun `should register a new user`() {
        val userJson = """{"username": "testuser", "password": "password", "email": "test@example.com"}"""

        mockMvc.perform(post("/api/users/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(status().isCreated)
    }

    @Test
    fun `should login an existing user`() {
        val loginJson = """{"username": "testuser", "password": "password"}"""

        mockMvc.perform(post("/api/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(loginJson))
            .andExpect(status().isOk)
    }

    @Test
    fun `should get user profile`() {
        val userId = 1L

        mockMvc.perform(get("/api/users/$userId"))
            .andExpect(status().isOk)
    }
}