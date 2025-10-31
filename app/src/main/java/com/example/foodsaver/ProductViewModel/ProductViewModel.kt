package com.example.foodsaver.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodsaver.data.model.Product
import com.example.foodsaver.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val result = productRepository.getAvailableProducts()
                _isLoading.value = false
                result.fold(
                    onSuccess = { productList ->
                        _products.value = productList
                    },
                    onFailure = { exception ->
                        _error.value = exception.message ?: "Error desconocido"
                    }
                )
            } catch (e: Exception) {
                _isLoading.value = false
                _error.value = e.message ?: "Error de conexión"
            }
        }
    }

    fun searchProducts(
        name: String? = null,
        category: String? = null,
        isForSale: Boolean? = null,
        location: String? = null
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val result = productRepository.searchProducts(name, category, isForSale, location)
                _isLoading.value = false
                result.fold(
                    onSuccess = { productList ->
                        _products.value = productList
                    },
                    onFailure = { exception ->
                        _error.value = exception.message ?: "Error en la búsqueda"
                    }
                )
            } catch (e: Exception) {
                _isLoading.value = false
                _error.value = e.message ?: "Error de conexión en búsqueda"
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    fun refreshProducts() {
        loadProducts()
    }
}