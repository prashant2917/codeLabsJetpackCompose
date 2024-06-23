package com.pocket.codelabsjetpackcompose.inventory.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pocket.codelabsjetpackcompose.inventory.data.InventoryItemsRepository

/**
 * ViewModel to retrieve, update and delete an item from the [ItemsRepository]'s data source.
 */
class InventoryItemDetailsViewModel(
    private val itemsRepository: InventoryItemsRepository
) : ViewModel() {

    //  private val itemId: Int = checkNotNull(savedStateHandle[Constants.NavigationRouteConstants.INVENTORY_ITEM_ID_ARG])

    var inventoryItemDetailUiState by mutableStateOf(InventoryItemDetailsUiState())
        private set

    suspend fun getInventoryDetails(id: Int) {
        itemsRepository.getItemStream(id).collect { inventoryItem ->
            if (inventoryItem != null) {
                inventoryItemDetailUiState =
                    InventoryItemDetailsUiState(false, inventoryItem.toInventoryItemDetails())
            }
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for ItemDetailsScreen
 */
data class InventoryItemDetailsUiState(
    val outOfStock: Boolean = true,
    val inventoryItemDetails: InventoryItemDetails = InventoryItemDetails()
)
