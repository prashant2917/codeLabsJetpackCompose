package com.pocket.codelabsjetpackcompose.inventory.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pocket.codelabsjetpackcompose.affirmations.utils.Constants


class InventoryItemEditViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var inventoryItemUiState by mutableStateOf(InventoryItemUiState())
        private set

    private val itemId: Int =
        checkNotNull(savedStateHandle[Constants.NavigationRouteConstants.INVENTORY_ITEM_ID_ARG])

    private fun validateInput(uiState: InventoryItemDetails = inventoryItemUiState.inventoryItemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}
