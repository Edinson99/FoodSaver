package com.example.foodsaver.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TestConnectionScreen(
    viewModel: TestConnectionViewModel = viewModel()
) {
    val connectionState by viewModel.connectionState.collectAsState()
    val products by viewModel.products.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🔧 Prueba de Conexión",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(Modifier.height(24.dp))
        
        // Botones de prueba
        Button(
            onClick = { viewModel.testBackendHealth() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("🏥 Probar Estado del Backend")
        }
        
        Spacer(Modifier.height(12.dp))
        
        Button(
            onClick = { viewModel.testConnection() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("📦 Obtener Productos")
        }
        
        Spacer(Modifier.height(24.dp))
        
        // Estado de conexión - SOLUCION: Usar when con variable local
        val currentState = connectionState  // ← AGREGAR ESTA LÍNEA
        when (currentState) {  // ← USAR currentState en lugar de connectionState
            is ConnectionState.Loading -> {
                CircularProgressIndicator()
                Spacer(Modifier.height(16.dp))
                Text("Conectando...", style = MaterialTheme.typography.bodyMedium)
            }
            is ConnectionState.Success -> {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                ) {
                    Text(
                        text = currentState.message,  // ← USAR currentState
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF2E7D32)
                    )
                }
            }
            is ConnectionState.Error -> {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE))
                ) {
                    Text(
                        text = currentState.message,  // ← USAR currentState
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFC62828)
                    )
                }
            }
            is ConnectionState.Idle -> {
                Text(
                    text = "Presiona un botón para probar la conexión",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
        
        Spacer(Modifier.height(16.dp))
        
        // Lista de productos si los hay
        if (products.isNotEmpty()) {
            Text(
                text = "📋 Productos obtenidos:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(Modifier.height(8.dp))
            
            LazyColumn {
                items(products) { product ->
                    ProductTestCard(product)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun ProductTestCard(product: com.example.foodsaver.data.network.dto.ProductDto) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text("ID: ${product.id}", style = MaterialTheme.typography.bodySmall)
            Text("Categoría: ${product.category}", style = MaterialTheme.typography.bodySmall)
            Text("Estado: ${product.status}", style = MaterialTheme.typography.bodySmall)
            if (product.price != null) {
                Text("Precio: $${product.price}", style = MaterialTheme.typography.bodySmall)
            } else {
                Text("DONACIÓN", style = MaterialTheme.typography.bodySmall, color = Color(0xFFFF9800))
            }
        }
    }
}