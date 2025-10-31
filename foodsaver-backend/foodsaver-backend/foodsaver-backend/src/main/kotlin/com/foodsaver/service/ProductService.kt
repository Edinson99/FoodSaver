package com.foodsaver.service

import com.foodsaver.model.Product
import com.foodsaver.model.ProductCategory
import com.foodsaver.model.ProductStatus
import com.foodsaver.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun getAvailableProducts(): List<Product> {
        return productRepository.findByStatus(ProductStatus.AVAILABLE)
    }

    fun searchProducts(
        name: String? = null,
        category: ProductCategory? = null,  // CAMBIO: usar enum
        isForSale: Boolean? = null,
        location: String? = null
    ): List<Product> {
        return when {
            name != null && category != null && isForSale != null ->
                productRepository.findByNameContainingIgnoreCaseAndCategoryAndIsForSaleAndStatus(
                    name, category, isForSale, ProductStatus.AVAILABLE
                )
            name != null && category != null ->
                productRepository.findByNameContainingIgnoreCaseAndCategoryAndStatus(
                    name, category, ProductStatus.AVAILABLE
                )
            name != null && isForSale != null ->
                productRepository.findByNameContainingIgnoreCaseAndIsForSaleAndStatus(
                    name, isForSale, ProductStatus.AVAILABLE
                )
            category != null && isForSale != null ->
                productRepository.findByCategoryAndIsForSaleAndStatus(
                    category, isForSale, ProductStatus.AVAILABLE
                )
            name != null ->
                productRepository.findByNameContainingIgnoreCaseAndStatus(
                    name, ProductStatus.AVAILABLE
                )
            category != null ->
                productRepository.findByCategoryAndStatus(category, ProductStatus.AVAILABLE)
            isForSale != null ->
                productRepository.findByIsForSaleAndStatus(isForSale, ProductStatus.AVAILABLE)
            else -> getAvailableProducts()
        }
    }

    fun getProductById(id: Long): Product? {
        return productRepository.findById(id).orElse(null)
    }
}