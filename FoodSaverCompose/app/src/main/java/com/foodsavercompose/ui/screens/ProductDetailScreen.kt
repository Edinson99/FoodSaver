// ProductDetailScreen.kt
package com.foodsavercompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.foodsavercompose.model.Product

@Composable
fun ProductDetailScreen(product: Product) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = product.name) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Product Details", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Description: ${product.description}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price: $${product.price}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* Handle purchase action */ }) {
                Text(text = "Purchase")
            }
        }
    }
}