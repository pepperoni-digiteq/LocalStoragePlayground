package com.digiteqautomotive.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.digiteqautomotive.myapplication.domain.models.ItemModel
import com.digiteqautomotive.myapplication.ui.ItemsViewModel

@Composable
fun RemoteScreen(viewModel: ItemsViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    var idInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = idInput,
            onValueChange = { idInput = it },
            label = { Text("Enter Character ID") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.getItem(idInput) },
            enabled = idInput.isNotBlank(),
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Load Character")
        }

        Spacer(modifier = Modifier.height(24.dp))

        when {
            uiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            uiState.item != null -> {
                ItemScreen(item = uiState.item!!)
            }

            uiState.error != null -> {
                Text("Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun ItemScreen(item: ItemModel) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        InfoRow("Gender", item.gender)
        InfoRow("Birth Year", item.birthYear)
        InfoRow("Height", item.height)
        InfoRow("Mass", item.mass)
        InfoRow("Homeworld URL", item.homeworldUrl)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = item.description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "$label: ",
            fontWeight = FontWeight.SemiBold
        )
        Text(text = value)
    }
}