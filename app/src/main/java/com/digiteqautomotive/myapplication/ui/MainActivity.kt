package com.digiteqautomotive.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.digiteqautomotive.myapplication.data.database.AppDatabase
import com.digiteqautomotive.myapplication.data.datastore.UserPreferencesDataStore
import com.digiteqautomotive.myapplication.data.repository.UserRepositoryImpl
import com.digiteqautomotive.myapplication.remote.repository.ItemsRepositoryImpl
import com.digiteqautomotive.myapplication.ui.navigation.AppNavHost
import com.digiteqautomotive.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var itemsViewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create dependencies manually
        val userDao = AppDatabase.getDatabase(this).userDao()
        val userRepository = UserRepositoryImpl(userDao)
        val itemsRepository = ItemsRepositoryImpl()
        val userPreferencesDataStore = UserPreferencesDataStore(this)

        userViewModel = UserViewModel(userRepository, userPreferencesDataStore)
        itemsViewModel = ItemsViewModel(itemsRepository)

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AppNavHost(userViewModel, itemsViewModel)
            }
        }
    }
}