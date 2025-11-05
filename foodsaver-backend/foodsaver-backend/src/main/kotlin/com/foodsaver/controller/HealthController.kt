package com.foodsaver.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class HealthController {

    @GetMapping("/health")
    fun health(): String {
        return "✅ Backend is alive!"
    }

    @GetMapping("/ping")
    fun ping(): Map<String, Any> {
        return mapOf(
            "status" to "OK",
            "timestamp" to System.currentTimeMillis(),
            "message" to "Pong!",
            "port" to "8091"
        )
    }
}