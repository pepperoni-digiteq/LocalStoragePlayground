package com.digiteqautomotive.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.digiteqautomotive.myapplication.ui.navigation.Screens

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Android Local Storage Demo", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate(Screens.Room.name) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Room Database") }

        Spacer(modifier = Modifier.height(8.dp))

//        Button(
//            onClick = { navController.navigate(Screens.DataStore.name) },
//            modifier = Modifier.fillMaxWidth()
//        ) { Text("Encrypted DataStore") }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Button(
//            onClick = { navController.navigate(Screens.FileStorage.name) },
//            modifier = Modifier.fillMaxWidth()
//        ) { Text("File Storage") }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Button(
//            onClick = { navController.navigate(Screens.Permissions.name) },
//            modifier = Modifier.fillMaxWidth()
//        ) { Text("Permissions & Access") }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Button(
//            onClick = { navController.navigate(Screens.Encryption.name) },
//            modifier = Modifier.fillMaxWidth()
//        ) { Text("Encryption & Security") }
    }
}