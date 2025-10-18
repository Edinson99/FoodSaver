package com.example.foodsaver.data.network.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("name")
    val name: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("quantity")
    val quantity: String,

    @SerializedName("price")
    val price: Double? = null,

    @SerializedName("description")
    val description: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("photoUrl")
    val photoUrl: String? = null,

    @SerializedName("isForSale")
    val isForSale: Boolean,

    @SerializedName("status")
    val status: String, // "AVAILABLE", "SOLD", "DONATED", "EXPIRED"

    @SerializedName("expiryDate")
    val expiryDate: String,

    @SerializedName("vendor")
    val vendor: UserDto? = null,

    @SerializedName("vendorId")
    val vendorId: Long? = null,

    @SerializedName("createdAt")
    val createdAt: String? = null
)

data class CreateProductRequest(
    @SerializedName("name")
    val name: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("quantity")
    val quantity: String,

    @SerializedName("price")
    val price: Double? = null,

    @SerializedName("description")
    val description: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("isForSale")
    val isForSale: Boolean,

    @SerializedName("expiryDate")
    val expiryDate: String
)

data class ProductSearchRequest(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("isForSale")
    val isForSale: Boolean? = null,

    @SerializedName("location")
    val location: String? = null
)