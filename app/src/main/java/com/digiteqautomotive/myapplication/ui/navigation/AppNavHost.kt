package com.digiteqautomotive.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.digiteqautomotive.myapplication.ui.UserViewModel
import com.digiteqautomotive.myapplication.ui.screens.HomeScreen
import com.digiteqautomotive.myapplication.ui.screens.RoomScreen

@Composable
fun AppNavHost(viewModel: UserViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Home.name) {
        composable(Screens.Home.name) { HomeScreen(navController) }
        composable(Screens.Room.name) { RoomScreen(viewModel) }
//        composable(Screens.DataStore.name) { DataStoreScreen() }
//        composable(Screens.FileStorage.name) { FileStorageScreen() }
//        composable(Screens.Permissions.name) { PermissionsScreen() }
//        composable(Screens.Encryption.name) { EncryptionScreen() }
    }
}