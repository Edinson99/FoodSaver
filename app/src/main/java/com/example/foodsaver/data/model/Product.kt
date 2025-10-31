package com.example.foodsaver.data.model



import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Product(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("category")
    val category: String = "",

    @SerializedName("quantity")
    val quantity: String = "",

    @SerializedName("price")
    val price: Double? = null,

    @SerializedName("description")
    val description: String = "",

    @SerializedName("location")
    val location: String = "",

    @SerializedName("isForSale")
    val isForSale: Boolean = true,

    @SerializedName("status")
    val status: String = "AVAILABLE",

    @SerializedName("expiryDate")
    val expiryDate: LocalDateTime? = null,

    @SerializedName("vendorId")
    val vendorId: Long = 0,

    @SerializedName("imageUrl")
    val imageUrl: String? = null,

    @SerializedName("createdAt")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @SerializedName("updatedAt")
    val updatedAt: LocalDateTime = LocalDateTime.now()
)