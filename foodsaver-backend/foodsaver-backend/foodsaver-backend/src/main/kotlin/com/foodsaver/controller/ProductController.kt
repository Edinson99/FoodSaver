package com.foodsaver.controller

import com.foodsaver.model.Product
import com.foodsaver.model.ProductCategory
import com.foodsaver.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = ["*"])
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productService.getAllProducts())
    }

    @GetMapping("/available")
    fun getAvailableProducts(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productService.getAvailableProducts())
    }

    @GetMapping("/search")
    fun searchProducts(
        @RequestParam(required = false) name: String? = null,
        @RequestParam(required = false) category: String? = null,  // Recibir como String
        @RequestParam(required = false) isForSale: Boolean? = null,
        @RequestParam(required = false) location: String? = null
    ): ResponseEntity<List<Product>> {
        // Convertir String a enum si es necesario
        val categoryEnum = category?.let {
            try {
                ProductCategory.valueOf(it.uppercase())
            } catch (e: IllegalArgumentException) {
                null
            }
        }

        return ResponseEntity.ok(
            productService.searchProducts(name, categoryEnum, isForSale, location)
        )
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<Product> {
        return productService.getProductById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/test")
    fun testEndpoint(): ResponseEntity<String> {
        return ResponseEntity.ok("✅ Backend funcionando correctamente!")
    }
}