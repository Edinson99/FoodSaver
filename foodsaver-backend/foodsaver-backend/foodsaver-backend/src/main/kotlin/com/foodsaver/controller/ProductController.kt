package com.foodsaver.controller

import com.foodsaver.model.Product
import com.foodsaver.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = ["*"])
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return try {
            ResponseEntity.ok("✅ Backend funcionando correctamente!")
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body("❌ Error: ${e.message}")
        }
    }

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<Product>> {
        return try {
            val products = productService.getAllProducts()
            ResponseEntity.ok(products)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(emptyList())
        }
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<Product> {
        return try {
            val product = productService.getProductById(id)
            if (product != null) {
                ResponseEntity.ok(product)
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.internalServerError().build()
        }
    }

    @PostMapping
    fun createProduct(@RequestBody product: Product): ResponseEntity<Product> {
        return try {
            val savedProduct = productService.createProduct(product)
            ResponseEntity.ok(savedProduct)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().build()
        }
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody product: Product): ResponseEntity<Product> {
        return try {
            val updatedProduct = productService.updateProduct(id, product)
            if (updatedProduct != null) {
                ResponseEntity.ok(updatedProduct)
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.internalServerError().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            productService.deleteProduct(id)
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.internalServerError().build()
        }
    }
}