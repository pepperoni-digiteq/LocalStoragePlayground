package com.digiteqautomotive.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digiteqautomotive.myapplication.domain.models.ItemModel
import com.digiteqautomotive.myapplication.domain.repository.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemsViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun getItem(id: String) {
        _uiState.value = UiState(isLoading = true)

        viewModelScope.launch {
            try {
//                val item = itemsRepository.testRetrofit(id)
//                val item = itemsRepository.testKtor(id)
                val item = itemsRepository.testOkHttp(id)
                _uiState.value = UiState(item = item)
            } catch (e: Exception) {
                _uiState.value = UiState(error = e.message ?: "Unknown error")
            }
        }
    }

    override fun onCleared() {
        itemsRepository.clear()
        super.onCleared()
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val item: ItemModel? = null,
    val error: String? = null
)