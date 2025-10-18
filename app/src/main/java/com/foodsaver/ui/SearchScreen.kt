package com.example.foodsaver.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Menu  // ← CAMBIAR A ESTE
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    var showFilters by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Buscar productos",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        // Barra de búsqueda principal
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Buscar por nombre del producto") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            },
            trailingIcon = {
                IconButton(onClick = { showFilters = !showFilters }) {
                    Icon(
                        Icons.Default.Menu, // ← CAMBIAR AQUÍ
                        contentDescription = "Filtros",
                        tint = if (showFilters) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Panel de filtros (expandible)
        if (showFilters) {
            Spacer(Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Filtros",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(16.dp))

                    // Filtros simples
                    Text("Categoría:")
                    Spacer(Modifier.height(8.dp))
                    Row {
                        listOf("FRUTAS", "VERDURAS", "LACTEOS").forEach { category ->
                            FilterChip(
                                onClick = { },
                                label = { Text(category) },
                                selected = false,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Tipo:")
                    Spacer(Modifier.height(8.dp))
                    Row {
                        listOf("VENTA", "DONACIÓN").forEach { type ->
                            FilterChip(
                                onClick = { },
                                label = { Text(type) },
                                selected = false,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Resultados de búsqueda
        Text(
            text = "Productos encontrados",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(8.dp))

        // Lista de productos de ejemplo
        LazyColumn {
            items(getSampleProducts()) { product ->
                ProductCard(product)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ProductCard(product: SampleProduct) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text("Categoría: ${product.category}")
            Text("Ubicación: ${product.location}")
            if (product.isForSale) {
                Text("Precio: ${product.price}", color = Color(0xFF4CAF50))
            } else {
                Text("DONACIÓN", color = Color(0xFFFF9800))
            }
        }
    }
}

data class SampleProduct(
    val name: String,
    val category: String,
    val location: String,
    val price: String,
    val isForSale: Boolean
)

fun getSampleProducts(): List<SampleProduct> {
    return listOf(
        SampleProduct("Tomates frescos", "VERDURAS", "Centro", "$2000/kg", true),
        SampleProduct("Pan del día anterior", "PANADERIA", "Norte", "Donación", false),
        SampleProduct("Manzanas rojas", "FRUTAS", "Sur", "$1500/kg", true)
    )
}