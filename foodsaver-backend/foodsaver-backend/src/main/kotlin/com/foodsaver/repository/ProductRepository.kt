package com.foodsaver.repository

import com.foodsaver.model.Product
import com.foodsaver.model.ProductCategory
import com.foodsaver.model.ProductStatus
import com.foodsaver.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findByName(name: String): List<Product>
    fun findByStatus(status: ProductStatus): List<Product>
    fun findByCategory(category: ProductCategory): List<Product>
    fun findByVendor(vendor: User): List<Product>
    fun findByIsForSale(isForSale: Boolean): List<Product>
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% AND p.status = 'AVAILABLE'")
    fun findByNameContainingAndAvailable(@Param("name") name: String): List<Product>
    
    @Query("""
        SELECT p FROM Product p 
        WHERE (:category IS NULL OR p.category = :category)
        AND (:isForSale IS NULL OR p.isForSale = :isForSale)
        AND (:location IS NULL OR p.location LIKE %:location%)
        AND p.status = 'AVAILABLE'
        ORDER BY p.createdAt DESC
    """)
    fun findAvailableProductsWithFilters(
        @Param("category") category: ProductCategory?,
        @Param("isForSale") isForSale: Boolean?,
        @Param("location") location: String?
    ): List<Product>
}