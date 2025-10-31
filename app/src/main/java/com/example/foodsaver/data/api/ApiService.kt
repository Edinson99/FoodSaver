package com.example.foodsaver.data.api



import com.example.foodsaver.data.model.Product
import com.example.foodsaver.data.repository.CreateProductRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Endpoints de productos
    @GET("api/products")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("api/products/available")
    suspend fun getAvailableProducts(): Response<List<Product>>

    @GET("api/products/search")
    suspend fun searchProducts(
        @Query("name") name: String? = null,
        @Query("category") category: String? = null,
        @Query("isForSale") isForSale: Boolean? = null,
        @Query("location") location: String? = null
    ): Response<List<Product>>

    @GET("api/products/{id}")
    suspend fun getProductById(@Path("id") id: Long): Response<Product>

    @POST("api/products")
    suspend fun createProduct(@Body product: CreateProductRequest): Response<Product>

    @GET("api/products/vendor/{vendorId}")
    suspend fun getProductsByVendor(@Path("vendorId") vendorId: Long): Response<List<Product>>

    @GET("api/products/donations")
    suspend fun getDonations(): Response<List<Product>>

    @GET("api/products/sales")
    suspend fun getSales(): Response<List<Product>>

    // Test endpoints
    @GET("api/products/test")
    suspend fun testProducts(): Response<String>
}