package com.example.foodsaver.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReportsScreen() {
    val report = getSampleReport()
    val productHistory = getSampleProductHistory()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text("Mis reportes", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
        }

        // Resumen general
        item {
            ReportSummaryCard(report)
            Spacer(Modifier.height(16.dp))
        }

        // Gráfico simple (representación textual)
        item {
            ImpactCard(report)
            Spacer(Modifier.height(16.dp))
        }

        item {
            Text("Historial de productos", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
        }

        // Lista de productos
        items(productHistory) { product ->
            ProductHistoryItem(product)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun ReportSummaryCard(report: ProductReport) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Resumen del mes",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem("Publicados", report.totalPublished.toString(), Color(0xFF2196F3))
                StatItem("Vendidos", report.totalSold.toString(), Color(0xFF4CAF50))
                StatItem("Donados", report.totalDonated.toString(), Color(0xFFFF9800))
            }

            if (report.totalRevenue > 0) {
                Spacer(Modifier.height(16.dp))
                Divider()
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Ingresos generados: $${String.format("%.2f", report.totalRevenue)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            color = color,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun ImpactCard(report: ProductReport) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "🌱 Tu impacto ambiental",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )
            Spacer(Modifier.height(8.dp))

            val totalSaved = report.totalSold + report.totalDonated
            Text(
                text = "Has evitado que $totalSaved productos se desperdicien",
                style = MaterialTheme.typography.bodyMedium
            )

            if (report.totalDonated > 0) {
                Text(
                    text = "Y has ayudado con ${report.totalDonated} donaciones",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFFF6F00)
                )
            }
        }
    }
}

@Composable
fun ProductHistoryItem(product: ProductSummary) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.productName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = "Publicado: ${dateFormat.format(product.publishDate)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                product.completedDate?.let {
                    Text(
                        text = "Completado: ${dateFormat.format(it)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                StatusChip(product.status)
                product.price?.let { price ->
                    if (product.status == ProductStatus.SOLD) {
                        Text(
                            text = "$${String.format("%.2f", price)}",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatusChip(status: ProductStatus) {
    val (text, color) = when (status) {
        ProductStatus.AVAILABLE -> "Disponible" to Color(0xFF2196F3)
        ProductStatus.SOLD -> "Vendido" to Color(0xFF4CAF50)
        ProductStatus.DONATED -> "Donado" to Color(0xFFFF9800)
        ProductStatus.EXPIRED -> "Expirado" to Color(0xFFF44336)
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = color.copy(alpha = 0.1f)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.bodySmall,
            color = color,
            fontWeight = FontWeight.Medium
        )
    }
}

// Modelos de datos
data class ProductReport(
    val totalPublished: Int,
    val totalSold: Int,
    val totalDonated: Int,
    val totalRevenue: Double,
    val period: String
)

data class ProductSummary(
    val productName: String,
    val category: String,
    val status: ProductStatus,
    val price: Double?,
    val publishDate: Date,
    val completedDate: Date?
)

enum class ProductStatus {
    AVAILABLE, SOLD, DONATED, EXPIRED
}

// Datos de ejemplo
fun getSampleReport(): ProductReport {
    return ProductReport(
        totalPublished = 15,
        totalSold = 8,
        totalDonated = 5,
        totalRevenue = 24500.0,
        period = "Octubre 2024"
    )
}

fun getSampleProductHistory(): List<ProductSummary> {
    return listOf(
        ProductSummary(
            productName = "Tomates frescos",
            category = "VERDURAS",
            status = ProductStatus.SOLD,
            price = 2000.0,
            publishDate = Date(System.currentTimeMillis() - 172800000),
            completedDate = Date(System.currentTimeMillis() - 86400000)
        ),
        ProductSummary(
            productName = "Pan del día anterior",
            category = "PANADERIA",
            status = ProductStatus.DONATED,
            price = null,
            publishDate = Date(System.currentTimeMillis() - 259200000),
            completedDate = Date(System.currentTimeMillis() - 172800000)
        ),
        ProductSummary(
            productName = "Manzanas",
            category = "FRUTAS",
            status = ProductStatus.AVAILABLE,
            price = 1500.0,
            publishDate = Date(System.currentTimeMillis() - 43200000),
            completedDate = null
        )
    )
}