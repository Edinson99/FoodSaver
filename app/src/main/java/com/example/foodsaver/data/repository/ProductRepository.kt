package com.example.foodsaver.data.repository
import com.example.foodsaver.data.api.ApiClient
import com.example.foodsaver.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository {

    private val apiService = ApiClient.apiService

    suspend fun getAvailableProducts(): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAvailableProducts()
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getAllProducts(): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAllProducts()
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun searchProducts(
        name: String? = null,
        category: String? = null,
        isForSale: Boolean? = null,
        location: String? = null
    ): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.searchProducts(name, category, isForSale, location)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun createProduct(product: CreateProductRequest): Result<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.createProduct(product)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getProductById(id: Long): Result<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProductById(id)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Producto no encontrado"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}

data class CreateProductRequest(
    val name: String,
    val category: String,
    val quantity: String,
    val price: Double?,
    val description: String,
    val location: String,
    val isForSale: Boolean,
    val expiryDate: String? = null,
    val vendorId: Long,
    val imageUrl: String? = null
)