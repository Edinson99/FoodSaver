package com.example.foodsaver.data.model

import java.util.Date

data class ProductReport(
    val totalPublished: Int,
    val totalSold: Int,
    val totalDonated: Int,
    val totalRevenue: Double,
    val period: String
)

data class ProductSummary(
    val productName: String,
    val category: String,
    val status: ProductStatus,
    val price: Double?,
    val publishDate: Date,
    val completedDate: Date?
)