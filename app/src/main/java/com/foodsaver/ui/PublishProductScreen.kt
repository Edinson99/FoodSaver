package com.example.foodsaver.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment

@Composable
fun PublishProductScreen(
    onPublish: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    var productName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("FRUTAS") }
    var isForSale by remember { mutableStateOf(true) }

    val categories = listOf("FRUTAS", "VERDURAS", "LACTEOS", "CARNES", "PANADERIA", "OTROS")
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Header personalizado sin TopAppBar
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Publicar producto",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Nombre del producto
            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("Nombre del producto") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(16.dp))

            // Descripción
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(Modifier.height(16.dp))

            // Categoría
            Text(
                text = "Categoría",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(8.dp))

            // RadioButtons para categorías
            Column {
                categories.chunked(2).forEach { rowCategories ->
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowCategories.forEach { category ->
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .selectable(
                                        selected = selectedCategory == category,
                                        onClick = { selectedCategory = category }
                                    )
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedCategory == category,
                                    onClick = { selectedCategory = category }
                                )
                                Text(
                                    text = category,
                                    modifier = Modifier.padding(start = 8.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                        // Llenar el espacio si hay solo un elemento en la fila
                        if (rowCategories.size == 1) {
                            Spacer(Modifier.weight(1f))
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Tipo: Venta o Donación
            Text(
                text = "Tipo de publicación",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(8.dp))

            Row {
                // Opción Venta
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = isForSale,
                            onClick = { isForSale = true }
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isForSale,
                        onClick = { isForSale = true }
                    )
                    Text(
                        text = "Venta",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(Modifier.width(24.dp))

                // Opción Donación
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = !isForSale,
                            onClick = { isForSale = false }
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = !isForSale,
                        onClick = { isForSale = false }
                    )
                    Text(
                        text = "Donación",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Cantidad y Precio
            Row {
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                if (isForSale) {
                    Spacer(Modifier.width(16.dp))

                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        label = { Text("Precio ($)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Ubicación
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Ubicación") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(32.dp))

            // Botones
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancelar")
                }

                Spacer(Modifier.width(16.dp))

                Button(
                    onClick = onPublish,
                    modifier = Modifier.weight(1f),
                    enabled = productName.isNotBlank() &&
                            description.isNotBlank() &&
                            quantity.isNotBlank() &&
                            location.isNotBlank() &&
                            (!isForSale || price.isNotBlank())
                ) {
                    Text("Publicar")
                }
            }

            // Espaciado final
            Spacer(Modifier.height(16.dp))
        }
    }
}