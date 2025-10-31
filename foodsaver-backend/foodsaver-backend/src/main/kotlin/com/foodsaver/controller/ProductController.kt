package com.foodsaver.controller

import com.foodsaver.model.Product
import com.foodsaver.model.ProductStatus
import com.foodsaver.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = ["*"])
class ProductController(
    private val productService: ProductService
) {
    
    @GetMapping
    fun getAllProducts(): ResponseEntity<List<Product>> {
        val products = productService.getAllProducts()
        println("📦 Enviando ${products.size} productos")
        return ResponseEntity.ok(products)
    }
    
    @GetMapping("/available")
    fun getAvailableProducts(): ResponseEntity<List<Product>> {
        val products = productService.getAvailableProducts()
        println("📦 Enviando ${products.size} productos disponibles")
        return ResponseEntity.ok(products)
    }
    
    @GetMapping("/search")
    fun searchProducts(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) isForSale: Boolean?,
        @RequestParam(required = false) location: String?
    ): ResponseEntity<List<Product>> {
        println("🔍 Búsqueda: name=$name, category=$category, isForSale=$isForSale, location=$location")
        val products = productService.searchProducts(name, category, isForSale, location)
        println("📦 Encontrados ${products.size} productos")
        return ResponseEntity.ok(products)
    }
    
    @GetMapping("/vendor/{vendorId}")
    fun getProductsByVendor(@PathVariable vendorId: Long): ResponseEntity<List<Product>> {
        val products = productService.getProductsByVendor(vendorId)
        println("📦 Enviando ${products.size} productos del vendedor $vendorId")
        return ResponseEntity.ok(products)
    }
    
    @GetMapping("/category/{category}")
    fun getProductsByCategory(@PathVariable category: String): ResponseEntity<List<Product>> {
        val products = productService.getProductsByCategory(category)
        return ResponseEntity.ok(products)
    }
    
    @GetMapping("/donations")
    fun getDonations(): ResponseEntity<List<Product>> {
        val products = productService.getProductsByType(false)
        return ResponseEntity.ok(products)
    }
    
    @GetMapping("/sales")
    fun getSales(): ResponseEntity<List<Product>> {
        val products = productService.getProductsByType(true)
        return ResponseEntity.ok(products)
    }
    
    @PostMapping
    fun createProduct(@RequestBody productRequest: CreateProductRequest): ResponseEntity<Product> {
        return try {
            println("➕ Creando producto: ${productRequest.name}")
            
            val product = Product(
                name = productRequest.name,
                category = productRequest.category,
                quantity = productRequest.quantity,
                price = productRequest.price,
                description = productRequest.description,
                location = productRequest.location,
                isForSale = productRequest.isForSale,
                expiryDate = productRequest.expiryDate,
                vendorId = productRequest.vendorId,
                imageUrl = productRequest.imageUrl
            )
            
            val savedProduct = productService.createProduct(product)
            println("✅ Producto creado con ID: ${savedProduct.id}")
            ResponseEntity.status(HttpStatus.CREATED).body(savedProduct)
        } catch (e: Exception) {
            println("💥 Error creando producto: ${e.message}")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }
    
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<Product> {
        val product = productService.findById(id)
        return if (product != null) {
            ResponseEntity.ok(product)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody productRequest: CreateProductRequest): ResponseEntity<Product> {
        return try {
            val updatedProduct = Product(
                name = productRequest.name,
                category = productRequest.category,
                quantity = productRequest.quantity,
                price = productRequest.price,
                description = productRequest.description,
                location = productRequest.location,
                isForSale = productRequest.isForSale,
                expiryDate = productRequest.expiryDate,
                vendorId = productRequest.vendorId,
                imageUrl = productRequest.imageUrl
            )
            
            val result = productService.updateProduct(id, updatedProduct)
            if (result != null) {
                ResponseEntity.ok(result)
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }
    
    @PutMapping("/{id}/status")
    fun updateProductStatus(@PathVariable id: Long, @RequestBody statusRequest: UpdateStatusRequest): ResponseEntity<Product> {
        return try {
            val result = productService.updateProductStatus(id, statusRequest.status)
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
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        return if (productService.deleteProduct(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("🚀 Product Controller funcionando correctamente!")
    }
}

data class CreateProductRequest(
    val name: String,
    val category: String,
    val quantity: String,
    val price: Double?,
    val description: String,
    val location: String,
    val isForSale: Boolean,
    val expiryDate: java.time.LocalDateTime?,
    val vendorId: Long,
    val imageUrl: String? = null
)

data class UpdateStatusRequest(
    val status: ProductStatus
)