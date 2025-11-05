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
    val name: String = "",

    @Enumerated(EnumType.STRING)  // CAMBIO: Usar enum en lugar de String
    @Column(nullable = false)
    val category: ProductCategory = ProductCategory.OTROS,

    @Column(nullable = false)
    val quantity: String = "",

    @Column
    val price: Double? = null,

    @Column(length = 1000)
    val description: String = "",

    @Column(nullable = false)
    val location: String = "",

    @Column(nullable = false)
    val isForSale: Boolean = true,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: ProductStatus = ProductStatus.AVAILABLE,

    @Column
    val expiryDate: LocalDateTime? = null,

    @Column(nullable = false)
    val vendorId: Long = 0,

    @Column
    val imageUrl: String? = null,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)