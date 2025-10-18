package com.example.foodsaver.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProductDetailScreen(
    productId: String,
    onBackPressed: () -> Unit = {},
    onContactSeller: () -> Unit = {}
) {
    val product = getSampleProductDetail(productId)
    
    Column(modifier = Modifier.fillMaxSize()) {
        // Header simple sin TopAppBar experimental
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Detalle del producto",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        // Contenido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Imagen placeholder
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "📷\nFoto del producto",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
            
            Spacer(Modifier.height(16.dp))
            
            // Nombre del producto
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(Modifier.height(8.dp))
            
            // Precio o donación
            if (product.isForSale && product.price != null) {
                Text(
                    text = "$${String.format("%.0f", product.price)}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold
                )
            } else {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFFF9800).copy(alpha = 0.1f)
                ) {
                    Text(
                        text = "DONACIÓN",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFFF9800),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Spacer(Modifier.height(16.dp))
            
            // Información del producto
            InfoCard {
                InfoRow(
                    icon = Icons.Default.Person,
                    label = "Vendedor",
                    value = product.vendorName
                )
                InfoRow(
                    icon = Icons.Default.LocationOn,
                    label = "Ubicación",
                    value = product.location
                )
                InfoRow(
                    icon = Icons.Default.Info,
                    label = "Expira",
                    value = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(product.expiryDate)
                )
            }
            
            Spacer(Modifier.height(16.dp))
            
            // Descripción
            Text(
                text = "Descripción",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(Modifier.height(8.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = product.description,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            Spacer(Modifier.height(24.dp))
            
            // Botón de contacto
            Button(
                onClick = onContactSeller,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = if (product.isForSale) "Contactar vendedor" else "Solicitar donación",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun InfoCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
fun InfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

// Modelo temporal para el detalle
data class ProductDetail(
    val id: String,
    val name: String,
    val price: Double?,
    val description: String,
    val location: String,
    val vendorName: String,
    val isForSale: Boolean,
    val expiryDate: Date,
    val category: String
)

// Función para obtener datos de ejemplo
fun getSampleProductDetail(productId: String): ProductDetail {
    return ProductDetail(
        id = productId,
        name = "Tomates frescos",
        price = if (productId == "1") 2000.0 else null,
        description = "Tomates rojos frescos, perfectos para ensaladas y cocina. Cultivados localmente sin pesticidas. Excelente calidad y sabor.",
        location = "Centro - Mercado Principal",
        vendorName = "Fruver La Plaza",
        isForSale = productId == "1",
        expiryDate = Date(System.currentTimeMillis() + 86400000), // 1 día
        category = "VERDURAS"
    )
}
