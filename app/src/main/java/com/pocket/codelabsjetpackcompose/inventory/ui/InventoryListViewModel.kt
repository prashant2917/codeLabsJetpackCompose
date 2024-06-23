package com.pocket.codelabsjetpackcompose.inventory.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pocket.codelabsjetpackcompose.inventory.data.InventoryItem
import com.pocket.codelabsjetpackcompose.inventory.data.InventoryItemsRepository

/**
 * ViewModel to retrieve all items in the Room database.
 */
class InventoryListViewModel(private val itemsRepository: InventoryItemsRepository) : ViewModel() {
    var inventoryListUiState by mutableStateOf(InventoryListUiState())
        private set
    suspend fun getInventoryList() {
        itemsRepository.getAllItemsStream().collect {
            inventoryListUiState.itemList = it
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


data class InventoryListUiState(var itemList: List<InventoryItem> = listOf())
