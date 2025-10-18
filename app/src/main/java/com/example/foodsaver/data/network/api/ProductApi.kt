package com.example.foodsaver.data.network.api

import com.example.foodsaver.data.network.dto.*
import retrofit2.Response
import retrofit2.http.*

interface ProductApi {

    @GET("api/products")
    suspend fun getAllProducts(): Response<List<ProductDto>>

    @GET("api/products/available")
    suspend fun getAvailableProducts(): Response<List<ProductDto>>

    @GET("api/products/{id}")
    suspend fun getProductById(@Path("id") productId: Long): Response<ProductDto>

    @POST("api/products")
    suspend fun createProduct(
        @Body product: CreateProductRequest,
        @Header("Authorization") token: String
    ): Response<ProductDto>

    @PUT("api/products/{id}")
    suspend fun updateProduct(
        @Path("id") productId: Long,
        @Body product: ProductDto,
        @Header("Authorization") token: String
    ): Response<ProductDto>

    @DELETE("api/products/{id}")
    suspend fun deleteProduct(
        @Path("id") productId: Long,
        @Header("Authorization") token: String
    ): Response<Void>

    @GET("api/products/search")
    suspend fun searchProducts(
        @Query("name") name: String? = null,
        @Query("category") category: String? = null,
        @Query("isForSale") isForSale: Boolean? = null,
        @Query("location") location: String? = null
    ): Response<List<ProductDto>>

    @PUT("api/products/{id}/sold")
    suspend fun markAsSold(
        @Path("id") productId: Long,
        @Header("Authorization") token: String
    ): Response<ProductDto>

    @PUT("api/products/{id}/donated")
    suspend fun markAsDonated(
        @Path("id") productId: Long,
        @Header("Authorization") token: String
    ): Response<ProductDto>

    @GET("api/products/vendor/{vendorId}")
    suspend fun getProductsByVendor(
        @Path("vendorId") vendorId: Long,
        @Header("Authorization") token: String
    ): Response<List<ProductDto>>
}