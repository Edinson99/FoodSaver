package com.foodsaver.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    val name: String,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val category: ProductCategory,
    
    @Column(nullable = false)
    val quantity: String,
    
    val price: Double?,
    
    @Column(nullable = false)
    val description: String,
    
    @Column(nullable = false)
    val location: String,
    
    val photoUrl: String?,
    
    @Column(nullable = false)
    val isForSale: Boolean,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: ProductStatus = ProductStatus.AVAILABLE,
    
    @Column(nullable = false)
    val expiryDate: LocalDateTime,
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    val vendor: User,
    
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)

enum class ProductCategory {
    FRUTAS, VERDURAS, LACTEOS, CARNES, PANADERIA, OTROS
}

enum class ProductStatus {
    AVAILABLE, SOLD, DONATED, EXPIRED
}