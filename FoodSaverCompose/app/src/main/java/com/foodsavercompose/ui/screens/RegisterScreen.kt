// RegisterScreen.kt
package com.foodsavercompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Register") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Username") }
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Email") }
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )
                Button(onClick = { /* Handle registration logic */ }) {
                    Text("Register")
                }
            }
        }
    }
}