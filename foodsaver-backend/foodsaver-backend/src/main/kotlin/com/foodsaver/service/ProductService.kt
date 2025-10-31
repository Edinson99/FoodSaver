package com.foodsaver.service

import com.foodsaver.model.Product
import com.foodsaver.model.ProductStatus
import com.foodsaver.repository.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    
    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }
    
    fun getAvailableProducts(): List<Product> {
        return productRepository.findAvailableProducts()
    }
    
    fun createProduct(product: Product): Product {
        return productRepository.save(product.copy(
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            status = ProductStatus.AVAILABLE
        ))
    }
    
    fun searchProducts(
        name: String? = null,
        category: String? = null,
        isForSale: Boolean? = null,
        location: String? = null
    ): List<Product> {
        return productRepository.searchProducts(name, category, isForSale, location)
    }
    
    fun getProductsByVendor(vendorId: Long): List<Product> {
        return productRepository.findByVendorId(vendorId)
    }
    
    fun findById(id: Long): Product? {
        return productRepository.findById(id).orElse(null)
    }
    
    fun updateProduct(id: Long, updatedProduct: Product): Product? {
        return if (productRepository.existsById(id)) {
            productRepository.save(updatedProduct.copy(
                id = id,
                updatedAt = LocalDateTime.now()
            ))
        } else {
            null
        }
    }
    
    fun deleteProduct(id: Long): Boolean {
        return if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
            true
        } else {
            false
        }
    }
    
    fun updateProductStatus(id: Long, status: ProductStatus): Product? {
        val product = findById(id)
        return if (product != null) {
            updateProduct(id, product.copy(status = status))
        } else {
            null
        }
    }
    
    fun getExpiringProducts(): List<Product> {
        return productRepository.findExpiringProducts()
    }
    
    fun getProductsByCategory(category: String): List<Product> {
        return productRepository.findByCategory(category)
    }
    
    fun getProductsByType(isForSale: Boolean): List<Product> {
        return productRepository.findByIsForSale(isForSale)
    }
}