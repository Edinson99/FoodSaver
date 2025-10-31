package com.foodsaver.repository

import com.foodsaver.model.Product
import com.foodsaver.model.ProductCategory
import com.foodsaver.model.ProductStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {

    fun findByStatus(status: ProductStatus): List<Product>

    fun findByCategoryAndStatus(category: ProductCategory, status: ProductStatus): List<Product>

    fun findByIsForSaleAndStatus(isForSale: Boolean, status: ProductStatus): List<Product>

    fun findByNameContainingIgnoreCaseAndStatus(name: String, status: ProductStatus): List<Product>

    fun findByNameContainingIgnoreCaseAndCategoryAndStatus(
        name: String,
        category: ProductCategory,
        status: ProductStatus
    ): List<Product>

    fun findByNameContainingIgnoreCaseAndIsForSaleAndStatus(
        name: String,
        isForSale: Boolean,
        status: ProductStatus
    ): List<Product>

    fun findByCategoryAndIsForSaleAndStatus(
        category: ProductCategory,
        isForSale: Boolean,
        status: ProductStatus
    ): List<Product>

    fun findByNameContainingIgnoreCaseAndCategoryAndIsForSaleAndStatus(
        name: String,
        category: ProductCategory,
        isForSale: Boolean,
        status: ProductStatus
    ): List<Product>

    fun findByVendorId(vendorId: Long): List<Product>
}