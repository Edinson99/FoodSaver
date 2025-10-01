package com.example.foodsaver.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun RegisterScreen(onRegister: () -> Unit) {
    var selectedRole by remember { mutableStateOf("Vendedor") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(32.dp)) {
        Text("Regístrate", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            RadioButton(
                selected = selectedRole == "Vendedor",
                onClick = { selectedRole = "Vendedor" }
            )
            Text("Vendedor")
            Spacer(Modifier.width(16.dp))
            RadioButton(
                selected = selectedRole == "Comprador / Beneficiario",
                onClick = { selectedRole = "Comprador / Beneficiario" }
            )
            Text("Comprador / Beneficiario")
        }
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Ubicación") }
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }
    }
}