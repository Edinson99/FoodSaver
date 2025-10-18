package com.example.foodsaver.data.model


import java.util.Date

data class Product(
    val id: String = "",
    val name: String,
    val category: ProductCategory,
    val quantity: String,
    val price: Double? = null, // null para donaciones
    val description: String,
    val location: String,
    val photoUrl: String? = null,
    val isForSale: Boolean, // true = venta, false = donación
    val status: ProductStatus = ProductStatus.AVAILABLE,
    val expiryDate: Date,
    val vendorId: String,
    val vendorName: String,
    val createdAt: Date = Date()
)

enum class ProductCategory {
    FRUTAS, VERDURAS, LACTEOS, CARNES, PANADERIA, OTROS
}

enum class ProductStatus {
    AVAILABLE, SOLD, DONATED, EXPIRED
}