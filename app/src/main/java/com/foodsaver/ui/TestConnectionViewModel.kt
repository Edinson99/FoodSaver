package com.example.foodsaver.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodsaver.data.network.ApiClient
import com.example.foodsaver.data.network.dto.ProductDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TestConnectionViewModel : ViewModel() {

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Idle)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    private val _products = MutableStateFlow<List<ProductDto>>(emptyList())
    val products: StateFlow<List<ProductDto>> = _products

    fun testConnection() {
        viewModelScope.launch {
            _connectionState.value = ConnectionState.Loading

            try {
                val response = ApiClient.productApi.getAvailableProducts()

                if (response.isSuccessful) {
                    val productList = response.body() ?: emptyList()
                    _products.value = productList
                    _connectionState.value = ConnectionState.Success("✅ Conexión exitosa! Productos obtenidos: ${productList.size}")
                } else {
                    _connectionState.value = ConnectionState.Error("❌ Error HTTP: ${response.code()} - ${response.message()}")
                }

            } catch (e: Exception) {
                _connectionState.value = ConnectionState.Error("❌ Error de conexión: ${e.message}")
            }
        }
    }

    fun testBackendHealth() {
        viewModelScope.launch {
            _connectionState.value = ConnectionState.Loading

            try {
                // Intentar conectar al backend
                val response = ApiClient.productApi.getAllProducts()

                if (response.isSuccessful) {
                    _connectionState.value = ConnectionState.Success("✅ Backend funcionando correctamente!")
                } else {
                    _connectionState.value = ConnectionState.Error("❌ Backend responde con error: ${response.code()}")
                }

            } catch (e: java.net.ConnectException) {
                _connectionState.value = ConnectionState.Error("❌ No se puede conectar al backend. ¿Está ejecutándose en puerto 8080?")
            } catch (e: Exception) {
                _connectionState.value = ConnectionState.Error("❌ Error: ${e.message}")
            }
        }
    }
}

sealed class ConnectionState {
    object Idle : ConnectionState()
    object Loading : ConnectionState()
    data class Success(val message: String) : ConnectionState()
    data class Error(val message: String) : ConnectionState()
}