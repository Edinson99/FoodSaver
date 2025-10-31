package com.example.foodsaver.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Delete  // CAMBIO: Usar Delete en lugar de Clear
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodsaver.viewmodel.ProductViewModel
import com.example.foodsaver.data.model.Product
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    productViewModel: ProductViewModel = viewModel()
) {
    var searchText by remember { mutableStateOf("") }
    var showFilters by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("") }

    val products by productViewModel.products.collectAsState()
    val isLoading by productViewModel.isLoading.collectAsState()
    val error by productViewModel.error.collectAsState()

    // Cargar productos al iniciar
    LaunchedEffect(Unit) {
        productViewModel.loadProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "🔍 Buscar productos",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                        Icons.Default.Settings,
                        contentDescription = "Filtros",
                        tint = if (showFilters) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Búsqueda instantánea al escribir
        LaunchedEffect(searchText) {
            if (searchText.length >= 2) {
                productViewModel.searchProducts(name = searchText)
            } else if (searchText.isEmpty()) {
                productViewModel.loadProducts()
            }
        }

        // Panel de filtros
        if (showFilters) {
            Spacer(modifier = Modifier.height(16.dp))
            FilterPanel(
                selectedCategory = selectedCategory,
                selectedType = selectedType,
                onCategoryChanged = { selectedCategory = it },
                onTypeChanged = { selectedType = it },
                onSearch = {
                    val isForSale = when (selectedType) {
                        "VENTA" -> true
                        "DONACIÓN" -> false
                        else -> null
                    }
                    productViewModel.searchProducts(
                        name = if (searchText.isBlank()) null else searchText,
                        category = if (selectedCategory.isBlank()) null else selectedCategory,
                        isForSale = isForSale
                    )
                },
                onClear = {
                    selectedCategory = ""
                    selectedType = ""
                    searchText = ""
                    productViewModel.loadProducts()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Estado de carga y errores
        when {
            isLoading -> {
                LoadingState()
            }
            error != null -> {
                ErrorState(
                    error = error!!,
                    onRetry = {
                        productViewModel.clearError()
                        productViewModel.loadProducts()
                    }
                )
            }
            products.isEmpty() -> {
                EmptyState()
            }
            else -> {
                ProductsList(products = products)
            }
        }
    }
}

@Composable
fun FilterPanel(
    selectedCategory: String,
    selectedType: String,
    onCategoryChanged: (String) -> Unit,
    onTypeChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onClear: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Filtros",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Filtros de búsqueda",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Filtros de categoría
            Text("📂 Categoría:")
            Spacer(modifier = Modifier.height(8.dp))

            val categories = listOf("FRUTAS", "VERDURAS", "LACTEOS", "PANADERIA", "CARNES")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = {
                            onCategoryChanged(if (selectedCategory == category) "" else category)
                        },
                        label = { Text(category) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Filtros de tipo
            Text("💰 Tipo:")
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedType == "VENTA",
                    onClick = {
                        onTypeChanged(if (selectedType == "VENTA") "" else "VENTA")
                    },
                    label = { Text("VENTA") }
                )
                FilterChip(
                    selected = selectedType == "DONACIÓN",
                    onClick = {
                        onTypeChanged(if (selectedType == "DONACIÓN") "" else "DONACIÓN")
                    },
                    label = { Text("DONACIÓN") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onClear,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = null)  // CAMBIO: Delete en lugar de Clear
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Limpiar")
                }

                Button(
                    onClick = onSearch,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Search, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Buscar")
                }
            }
        }
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxWidth().padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "🔄 Cargando productos...",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun ErrorState(
    error: String,
    onRetry: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "❌ Error: $error",
                color = Color(0xFFC62828),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC62828)
                )
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Reintentar")
            }
        }
    }
}

@Composable
fun EmptyState() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5))
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "📦",
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No se encontraron productos",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Intenta ajustar los filtros de búsqueda",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun ProductsList(products: List<Product>) {
    Column {
        Text(
            text = "📦 Productos encontrados (${products.size})",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                ProductCard(product = product)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header del producto
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // Precio o donación
                if (product.isForSale && product.price != null) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF4CAF50).copy(alpha = 0.1f)
                    ) {
                        Text(
                            text = "$${String.format("%.0f", product.price)}",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                } else {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFFF9800).copy(alpha = 0.1f)
                    ) {
                        Text(
                            text = "DONACIÓN",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            color = Color(0xFFFF9800),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Información del producto con iconos
            ProductInfoRow(icon = "📦", text = product.quantity)
            ProductInfoRow(icon = "🏷️", text = product.category)
            ProductInfoRow(icon = "📍", text = product.location)

            if (product.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "📝 ${product.description}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 2
                )
            }

            // Estado del producto
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = when (product.status) {
                    "AVAILABLE" -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                    "SOLD" -> Color(0xFF9E9E9E).copy(alpha = 0.1f)
                    "DONATED" -> Color(0xFF2196F3).copy(alpha = 0.1f)
                    else -> Color(0xFFFFC107).copy(alpha = 0.1f)
                }
            ) {
                Text(
                    text = when (product.status) {
                        "AVAILABLE" -> "✅ Disponible"
                        "SOLD" -> "💰 Vendido"
                        "DONATED" -> "🎁 Donado"
                        "EXPIRED" -> "⏰ Expirado"
                        else -> "📦 ${product.status}"
                    },
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = when (product.status) {
                        "AVAILABLE" -> Color(0xFF4CAF50)
                        "SOLD" -> Color(0xFF9E9E9E)
                        "DONATED" -> Color(0xFF2196F3)
                        else -> Color(0xFFFFC107)
                    },
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun ProductInfoRow(icon: String, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Text(
            text = icon,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}